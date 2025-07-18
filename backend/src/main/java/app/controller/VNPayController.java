package app.controller;

import app.service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/vnpay")
public class VNPayController {
    private final VNPayService vnPayService;

    @GetMapping("/ipn")
    public void handleVNPayIPN(HttpServletRequest request) {
        try {
            Map<String, String> fields = new HashMap<>();
            Enumeration<String> params = request.getParameterNames();

            while (params.hasMoreElements()) {
                String rawKey = params.nextElement();
                String key = URLEncoder.encode(rawKey, StandardCharsets.US_ASCII);
                String value = URLEncoder.encode(request.getParameter(rawKey), StandardCharsets.US_ASCII);
                if (value != null && !value.isEmpty()) {
                    fields.put(key, value);
                }
            }
            vnPayService.createTransaction(fields);
        } catch (Exception e) {
            log.error("Unknown error when handling VNPay IPN", e);
        }
    }


}
