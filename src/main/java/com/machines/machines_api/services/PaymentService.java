package com.machines.machines_api.services;

import com.machines.machines_api.models.dto.request.CheckoutRequestDTO;
import com.stripe.exception.StripeException;

public interface PaymentService {
    String createHostedCheckoutSession(CheckoutRequestDTO checkoutRequestDTO) throws StripeException;
}
