package app.utils;

import app.model.Order;
import app.model.serializable.UserOrders;
import app.repository.redis.UserOrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@RequiredArgsConstructor
public class VNPayUtils {
    public final String vnp_ReturnUrl = "/payment";
    private final UserOrdersRepository userOrdersRepository;

    @Value("${spring.vnpay.tmn-code}")
    public String vnp_TmnCode;
    @Value("${spring.vnpay.secret-key}")
    public String secretKey;
    @Value("${spring.vnpay.url}")
    public String vnp_PayUrl;
    @Value("${server.client-url}")
    private String clientUrl;

    private String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

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

    public void cacheTransaction(Map<String, String> params, List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return;
        }
        String vnp_TxnRef = params.get("vnp_TxnRef");
        long userId = orders.get(0).getUser().getId();

        UserOrders userOrders = new UserOrders();
        userOrders.setId(vnp_TxnRef);
        userOrders.setUserId(userId);
        for (Order order : orders) {
            userOrders.addOrder(order);
        }
        userOrdersRepository.save(userOrders);
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
