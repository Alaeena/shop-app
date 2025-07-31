package app.utils.vnpay;

import app.model.enums.OrderState;
import app.model.enums.VnpResponseCode;
import app.model.enums.VnpTransactionStatus;
import app.model.postgres.Order;
import app.model.postgres.Timestamp;
import app.model.postgres.Transaction;
import app.model.postgres.UserEntity;
import app.repository.postgres.OrderRepository;
import app.repository.postgres.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static app.model.enums.OrderState.PENDING;

@Component
@RequiredArgsConstructor
public abstract class VNPayUtils {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Value("${spring.vnpay.return-url}")
    protected String vnp_ReturnUrl;
    @Value("${spring.vnpay.tmn-code}")
    protected String vnp_TmnCode;
    @Value("${spring.vnpay.secret-key}")
    protected String secretKey;
    @Value("${spring.vnpay.url}")
    protected String vnp_PayUrl;
    @Value("${server.client-url}")
    protected String clientUrl;

    private String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    protected Transaction buildTransaction(Map<String, String> fields, Long userId, List<Long> orderIds) {
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
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Order> orders = orderRepository.findAllById(orderIds);
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

    public abstract void cacheTransaction(Map<String, String> params, List<Order> orders);

    public abstract Transaction getTransaction(Map<String, String> fields);

    public Map<String, String> getVNPayParams(long amount, String description, String orderType) {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_IpAddr = "127.0.0.1";
        String vnp_TxnRef = getRandomNumber(8);

        Map<String, String> params = new HashMap<>();
        params.put("vnp_Version", vnp_Version);
        params.put("vnp_Command", vnp_Command);
        params.put("vnp_TmnCode", vnp_TmnCode);
        params.put("vnp_ReturnUrl", clientUrl + vnp_ReturnUrl);

        params.put("vnp_Locale", "vn");
        params.put("vnp_CurrCode", "VND");
        params.put("vnp_IpAddr", vnp_IpAddr);

        params.put("vnp_Amount", String.valueOf(amount * 100));
        params.put("vnp_TxnRef", vnp_TxnRef);
        params.put("vnp_OrderType", orderType);
        params.put("vnp_OrderInfo", description);

        //Creation datetime of the invoice
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        params.put("vnp_CreateDate", vnp_CreateDate);

        //Expiration datetime of the invoice
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        params.put("vnp_ExpireDate", vnp_ExpireDate);

        return params;
    }

    public String createPaymentUrl(Map<String, String> params) {
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> it = fieldNames.iterator();

        while (it.hasNext()) {
            String fieldName = it.next();
            String fieldValue = params.get(fieldName);

            hashData.append(fieldName);
            hashData.append('=');
            hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
            //Build query
            query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
            query.append('=');
            query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
            if (it.hasNext()) {
                query.append('&');
                hashData.append('&');
            }
        }
        String vnp_SecureHash = hmacSHA512(hashData.toString());
        String queryUrl = query + "&vnp_SecureHash=" + vnp_SecureHash;
        return vnp_PayUrl + "?" + queryUrl;
    }

    public String hmacSHA512(final String data) {
        try {

            if (secretKey == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = secretKey.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }
}
