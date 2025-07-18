package app.service;

import app.model.Order;
import app.model.Timestamp;
import app.model.Transaction;
import app.model.UserEntity;
import app.model.enums.OrderState;
import app.model.enums.VnpResponseCode;
import app.model.enums.VnpTransactionStatus;
import app.model.serializable.UserOrders;
import app.repository.postgres.OrderRepository;
import app.repository.postgres.TransactionRepository;
import app.repository.postgres.UserRepository;
import app.repository.redis.UserOrdersRepository;
import app.utils.VNPayUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static app.model.enums.OrderState.PENDING;

@Slf4j
@Service
@RequiredArgsConstructor
public class VNPayService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final UserOrdersRepository userOrdersRepository;
    private final TransactionRepository transactionRepository;
    private final VNPayUtils vnpayUtils;

    public void createTransaction(Map<String, String> fields) {
        String vnp_SecureHash = fields.get("vnp_SecureHash");
        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");

        // Checksum validation
        String signValue = hashAllFields(fields);
        if (!signValue.equals(vnp_SecureHash)) {
            log.error("[VNPAYService] Invalid Checksum");
            return;
        }

        String vnp_TxnRef = fields.get("vnp_TxnRef");
        UserOrders userOrders = userOrdersRepository.findById(vnp_TxnRef).get();
        if (userOrders == null) {
            log.error("[VNPAYService] Invalid TxnRef");
            return;
        }
        Transaction transaction = buildTransaction(userOrders, fields);
        transactionRepository.save(transaction);
    }

    private Transaction buildTransaction(UserOrders userOrders, Map<String, String> fields) {
        String bankCode = fields.get("vnp_BankCode");
        String bankTranNo = fields.get("vnp_BankTranNo");
        String cardType = fields.get("vnp_CardType");

        String orderInfo = fields.get("vnp_OrderInfo");
        String transactionNo = fields.get("vnp_TransactionNo");
        String transactionStatus = fields.get("vnp_TransactionStatus");
        String responseCode = fields.get("vnp_ResponseCode");
        String txnRef = fields.get("vnp_TxnRef");

        // Parse amount (divide by 100 as per VNPAY doc)
        long amount = Long.parseLong(fields.get("vnp_Amount"));

        // Parse date (yyyyMMddHHmmss)
        String payDateStr = fields.get("vnp_PayDate");
        LocalDateTime payDate = null;
        if (payDateStr != null && !payDateStr.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            payDate = LocalDateTime.parse(payDateStr, formatter);
        }
        UserEntity user = userRepository.findById(userOrders.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Order> orders = orderRepository.findAllById(userOrders.getOrders());
        orders.forEach(order -> {
            order.setState(OrderState.PENDING);
            order.addTimestamp(new Timestamp(PENDING));
            order.setPaid(true);
        });
        if (orders.isEmpty()) {
            throw new IllegalArgumentException("Orders not found");
        }
        // Build transaction
        Transaction tx = new Transaction();
        tx.setOrders(orders);
        tx.setUser(user);
        tx.setAmount(amount);
        tx.setBankCode(bankCode);
        tx.setBankTransactionNo(bankTranNo);
        tx.setCardType(cardType);
        tx.setOrderInfo(orderInfo);
        tx.setTransactionNo(transactionNo);
        tx.setTxnRef(txnRef);
        tx.setPaymentDate(payDate);
        tx.setStatus(VnpTransactionStatus.fromCode(transactionStatus));
        tx.setResponseCode(VnpResponseCode.fromCode(responseCode));
        return tx;
    }


    private String hashAllFields(Map<String, String> fields) {
        List<String> sortedKeys = new ArrayList<>(fields.keySet());
        Collections.sort(sortedKeys);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sortedKeys.size(); i++) {
            String key = sortedKeys.get(i);
            String value = fields.get(key);
            sb.append(key).append("=").append(value);
            if (i != sortedKeys.size() - 1) {
                sb.append("&");
            }
        }
        return vnpayUtils.hmacSHA512(sb.toString());
    }
}
