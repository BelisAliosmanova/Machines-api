package com.machines.machines_api.models.dto.metadata;

import com.machines.machines_api.enums.OfferType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class OfferMetadata extends LinkedHashMap<String, String> {
    public static final String OFFER_ID_KEY = "offer_id";
    public static final String OFFER_TYPE_KEY = "offer_type";

    public OfferMetadata(UUID offerId, OfferType offerType) {
        this.put(OFFER_ID_KEY, offerId.toString());
        this.put(OFFER_TYPE_KEY, offerType.name());
    }

    public static boolean isOfferMetadata(Map<String, String> map) {
        return map.containsKey(OFFER_ID_KEY) && map.containsKey(OFFER_TYPE_KEY);
    }

    public UUID getOfferId() {
        return UUID.fromString(this.get(OFFER_ID_KEY));
    }

    public OfferType getOfferType() {
        return OfferType.valueOf(this.get(OFFER_TYPE_KEY));
    }
}
