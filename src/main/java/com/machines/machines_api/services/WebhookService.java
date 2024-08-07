package com.machines.machines_api.services;

import com.stripe.model.Event;
import com.stripe.model.StripeObject;

public interface WebhookService {
    Event constructEvent(String payload, String sigHeader);

    StripeObject convertStripeObject(Event event);

    void handleStripeEvent(Event event, StripeObject stripeObject);
}
