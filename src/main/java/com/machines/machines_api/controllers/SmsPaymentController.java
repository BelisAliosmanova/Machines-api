package com.machines.machines_api.controllers;

import com.machines.machines_api.enums.OfferType;
import com.machines.machines_api.services.OfferService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;

@RestController
public class SmsPaymentController {

    private static final Set<String> ALLOWED_IPS = Set.of("87.120.176.216", "35.234.105.159", "0:0:0:0:0:0:0:1");
    private static final Set<Integer> SERVICE_IDS = Set.of(21258, 21259, 21260);

    private final OfferService offerService;

    public SmsPaymentController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/sms/notify")
    public String handleSmsNotification(
            @RequestParam int servID,
            @RequestParam String fromnum,
            @RequestParam String message,
            @RequestParam String item,
            @RequestParam String smsID,
            @RequestParam String operator,
            @RequestParam String amount,
            @RequestParam String currency,
            @RequestParam String country,
            @RequestParam String billing_type,
            @RequestParam String billing_status,
            HttpServletRequest request) {

        // Verify request is coming from allowed IP
        String remoteAddress = request.getRemoteAddr();
        if (!ALLOWED_IPS.contains(remoteAddress)) {
            return "Unauthorized IP";
        }

        // Check if the service ID is valid
        if (!SERVICE_IDS.contains(servID)) {
            return "Invalid Service ID";
        }

        if(servID == 21258) {
            offerService.updateOfferType(UUID.fromString(item), OfferType.VIP);
        } else if (servID == 21260) {
            offerService.updateOfferType(UUID.fromString(item), OfferType.TOP);
        }

        String responseMessage = "Успешно плащане."; // Your response message

        // Send a response back to Mobio.bg to notify the user
        sendSmsResponse(servID, fromnum, smsID, responseMessage);

        return "OK";
    }

    private void sendSmsResponse(int servID, String toNumber, String smsID, String message) {
        String url = "http://mobio.bg/paynotify/pnsendsms.php";
        HttpURLConnection connection = null;

        try {
            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.toString());
            String requestUrl = String.format("%s?servID=%d&tonum=%s&smsID=%s&message=%s",
                    url, servID, toNumber, smsID, encodedMessage);

            // Open connection
            URL urlObj = new URL(requestUrl);
            connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);


            try (OutputStream os = connection.getOutputStream()) {
                os.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("SMS response sent successfully to Mobio.bg");
            } else {
                System.err.println("Failed to send SMS response: HTTP error code " + responseCode);
            }

        } catch (Exception e) {
            System.err.println("Failed to send SMS response: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}