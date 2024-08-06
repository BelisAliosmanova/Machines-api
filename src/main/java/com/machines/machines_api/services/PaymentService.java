package com.machines.machines_api.services;

import com.machines.machines_api.models.dto.request.PaymentRequestDTO;
import com.stripe.model.PaymentMethod;

public interface PaymentService {
    void save(PaymentRequestDTO paymentRequestDTO);

    PaymentMethod getPaymentMethod(String paymentMethod);
}
