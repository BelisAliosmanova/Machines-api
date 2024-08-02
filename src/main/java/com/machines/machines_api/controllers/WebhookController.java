package com.machines.machines_api.controllers;

import com.machines.machines_api.services.WebhookService;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/webhooks")
public class WebhookController {
    private final WebhookService webhookService;

    @PostMapping
    public ResponseEntity<String> webhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event = webhookService.constructEvent(payload, sigHeader);
        StripeObject stripeObject = webhookService.convertStripeObject(event);
        webhookService.handleStripeEvent(event, stripeObject);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
