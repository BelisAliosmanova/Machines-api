package com.machines.machines_api.models.dto.metadata;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OfferMetadata extends LinkedHashMap<String, String> {
    public static final String OFFER_ID_KEY = "offer_id";

    public OfferMetadata(String offerId) {
        this.put(OFFER_ID_KEY, offerId);
    }
}
