package com.machines.machines_api.models.dto.common;

import com.machines.machines_api.enums.PaymentProvider;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO extends BaseDTO {
    private String description;
    private String currency;
    private String status;
    private String paymentMethodType;
    private String metadata;
    private Long amount;
    private Long amountReceived;
    private String customerId;
    private String customerEmail;
    private String customerName;
    private PaymentProvider paymentProvider;
}
