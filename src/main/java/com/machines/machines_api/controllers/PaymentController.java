package com.machines.machines_api.controllers;

import com.machines.machines_api.models.dto.request.CheckoutRequestDTO;
import com.machines.machines_api.services.PaymentService;
import com.stripe.exception.StripeException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/checkout")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/hosted")
    public String hostedCheckoutURL(@Valid @RequestBody CheckoutRequestDTO checkoutRequestDTO) throws StripeException {
        return paymentService.createHostedCheckoutSession(checkoutRequestDTO);
    }
}
