package com.machines.machines_api.interfaces;


import java.math.BigDecimal;

public interface CheckoutProduct {
    String name = null;
    String checkoutId = null;
    String currency = null;
    BigDecimal unitAmountDecimal = null;

    String getName();

    String getCheckoutId();

    String getCurrency();

    BigDecimal getUnitAmountDecimal();
}
