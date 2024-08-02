package com.machines.machines_api.models.entity;

import com.machines.machines_api.interfaces.CheckoutProduct;
import com.machines.machines_api.models.baseEntity.BaseEntity;
import com.stripe.model.Price;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product extends BaseEntity implements CheckoutProduct {
    @NotBlank(message = "Име на продукта е задължително!")
    private String name;

    @NotBlank(message = "checkoutId на продукта е задължително!")
    private String checkoutId;

    @NotBlank(message = "Валутата на продукта е задължително!")
    private String currency;

    @DecimalMin(value = "0.0", message = "Цената трябва да е 0.0 или повече!")
    private BigDecimal unitAmountDecimal;

    public BigDecimal getUnitAmountDecimalInCents() {
        return unitAmountDecimal.multiply(BigDecimal.valueOf(100));
    }
}
