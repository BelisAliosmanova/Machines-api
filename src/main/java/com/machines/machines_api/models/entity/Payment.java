package com.machines.machines_api.models.entity;

import com.machines.machines_api.enums.PaymentProvider;
import com.machines.machines_api.models.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {
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

    @Enumerated(EnumType.STRING)
    private PaymentProvider paymentProvider;
}
