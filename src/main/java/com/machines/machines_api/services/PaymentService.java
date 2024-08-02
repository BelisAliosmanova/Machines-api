package com.machines.machines_api.services;

import com.machines.machines_api.models.dto.request.checkout.HostedCheckoutRequestDTO;
import com.machines.machines_api.models.dto.request.checkout.OfferCheckoutRequestDTO;
import com.stripe.exception.StripeException;

public interface PaymentService {
    String createHostedCheckoutSession(HostedCheckoutRequestDTO checkoutRequestDTO) throws StripeException;

    String createPromoteOfferHostedCheckoutSession(OfferCheckoutRequestDTO checkoutRequestDTO) throws StripeException;
}
