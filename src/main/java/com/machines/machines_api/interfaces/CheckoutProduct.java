package com.machines.machines_api.interfaces;


import com.stripe.model.Product;

import java.math.BigDecimal;

public interface CheckoutProduct {
    String name = null;
    String checkoutId = null;
    String currency = null;
    BigDecimal unitAmountDecimal = null;

    Product toProduct();
}
