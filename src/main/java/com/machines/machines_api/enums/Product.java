package com.machines.machines_api.enums;

import com.machines.machines_api.interfaces.CheckoutProduct;
import com.machines.machines_api.models.baseEntity.BaseEntity;
import com.stripe.model.Price;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private String name;
    private String checkoutId;
    private String currency;
    private BigDecimal unitAmountDecimal;

    @Override
    public com.stripe.model.Product toProduct() {
        com.stripe.model.Product product = new com.stripe.model.Product();
        Price price = new Price();

        product.setName(getName());
        product.setId(getCheckoutId());
        price.setCurrency(getCurrency());
        price.setUnitAmountDecimal(getUnitAmountDecimal());
        product.setDefaultPriceObject(price);

        return product;
    }
}
