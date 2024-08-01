package com.machines.machines_api.enums;

import com.machines.machines_api.interfaces.CheckoutProduct;
import com.machines.machines_api.models.dto.common.OfferTypeDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum OfferType implements CheckoutProduct {
    BASIC("Безплатна обява", "offer_basic", "bgn", BigDecimal.ZERO),
    VIP("ВИП обява", "offer_vip", "bgn", BigDecimal.valueOf(2.40)),
    TOP("ТОП обява", "offer_top", "bgn", BigDecimal.valueOf(6.00));

    private static final List<OfferType> OFFER_TYPES = new ArrayList<>();

    static {
        OFFER_TYPES.addAll(List.of(values()));
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

    public static List<OfferType> getOfferTypes() {
        return Collections.unmodifiableList(OFFER_TYPES);
    }

    public static List<CheckoutProduct> getOfferTypesAsCheckoutProducts() {
        return Collections.unmodifiableList(OFFER_TYPES);
    }

    public OfferTypeDTO toOfferTypeDTO() {
        OfferTypeDTO offerTypeDTO = new OfferTypeDTO();
        offerTypeDTO.setName(getName());
        offerTypeDTO.setCurrency(getCurrency());
        offerTypeDTO.setCheckoutId(getCheckoutId());
        offerTypeDTO.setUnitAmountDecimal(getUnitAmountDecimal());
        offerTypeDTO.setOfferType(this);

        return offerTypeDTO;
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
