package app.service;

import app.model.postgres.Transaction;
import app.repository.postgres.TransactionRepository;
import app.utils.vnpay.VNPayUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class VNPayService {
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
        Transaction transaction = vnpayUtils.getTransaction(fields);
        transactionRepository.save(transaction);
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
