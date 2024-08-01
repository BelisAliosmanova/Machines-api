package com.machines.machines_api.models.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.machines.machines_api.interfaces.CheckoutProduct;
import com.stripe.model.Product;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDTO extends BaseDTO implements CheckoutProduct {
    private String name;
    private String checkoutId;
    private String currency;
    private BigDecimal unitAmountDecimal;
}
