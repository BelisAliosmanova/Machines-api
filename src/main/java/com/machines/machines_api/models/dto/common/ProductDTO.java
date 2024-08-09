package com.machines.machines_api.models.dto.common;

import com.machines.machines_api.interfaces.CheckoutProduct;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDTO extends BaseDTO implements CheckoutProduct {
    private String name;
    private String checkoutId;
    private String currency;
    private BigDecimal unitAmountDecimal;
}
