package com.machines.machines_api.controllers;

import com.machines.machines_api.enums.OfferType;
import com.machines.machines_api.services.OfferService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.UUID;

@RestController
public class SmsPaymentController {

    private static final String ALLOWED_IP = "79.98.104.12"; // Mobio.bg IP address
    private static final Set<Integer> SERVICE_IDS = Set.of(21258, 21259, 21260);

    private final OfferService offerService;

    public SmsPaymentController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/sms/notify")
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

        // Verify request is coming from Mobio.bg IP
        String remoteAddress = request.getRemoteAddr();
        System.out.println(remoteAddress);
        if (!ALLOWED_IP.equals(remoteAddress) && !isLocalAddress(remoteAddress)) {
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

        String responseMessage = "Успешно плащане.";

        // Send a response back to Mobio.bg to notify the user
        sendSmsResponse(fromnum, smsID, responseMessage);

        return "OK";
    }

    private void sendSmsResponse(String toNumber, String smsID, String message) {
        // Use RestTemplate to send the response back to Mobio.bg
        String url = "http://mobio.bg/paynotify/pnsendsms.php";
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("servID", SERVICE_IDS.iterator().next())
                .queryParam("smsID", smsID)
                .queryParam("tonum", toNumber)
                .queryParam("message", URLEncoder.encode(message, StandardCharsets.UTF_8));

        try {
            System.out.println(restTemplate.getForObject(builder.toUriString(), String.class));
            System.out.println("SMS response sent successfully to Mobio.bg");
        } catch (Exception e) {
            System.err.println("Failed to send SMS response: " + e.getMessage());
        }
    }

    private boolean isLocalAddress(String ipAddress) {
        // Check if the IP address is a loopback address (localhost)
        return ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1");
    }
}
