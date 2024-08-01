package com.machines.machines_api.enums;

import com.machines.machines_api.interfaces.CheckoutProduct;
import com.stripe.model.Price;
import com.stripe.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum OfferType implements CheckoutProduct {
    BASIC("Безплатна обява", "offer_basic", "bgn", BigDecimal.ZERO),
    VIP("ВИП обява", "offer_vip", "bgn", BigDecimal.valueOf(2.40)),
    TOP("ТОП обява", "offer_top", "bgn", BigDecimal.valueOf(6.00));

    private static final List<CheckoutProduct> OFFER_CHECKOUT_PRODUCTS = new ArrayList<>();

    static {
        OFFER_CHECKOUT_PRODUCTS.addAll(List.of(values()));
    }

    private final String name;
    private final String checkoutId;
    private final String currency;
    private final BigDecimal unitAmountDecimal;

    OfferType(String name, String checkoutId, String currency, BigDecimal unitAmountDecimal) {
        this.name = name;
        this.checkoutId = checkoutId;
        this.currency = currency;
        this.unitAmountDecimal = unitAmountDecimal;
    }

    public static List<CheckoutProduct> getOfferCheckoutProducts() {
        return Collections.unmodifiableList(OFFER_CHECKOUT_PRODUCTS);
    }

    @Override
    public Product toProduct() {
        Product product = new Product();
        Price price = new Price();

        product.setName(this.name);
        product.setId(this.checkoutId);
        price.setCurrency(this.currency);
        price.setUnitAmountDecimal(this.unitAmountDecimal);
        product.setDefaultPriceObject(price);

        return product;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCheckoutId() {
        return this.checkoutId;
    }

    @Override
    public String getCurrency() {
        return this.currency;
    }

    @Override
    public BigDecimal getUnitAmountDecimal() {
        return this.unitAmountDecimal;
    }
}
