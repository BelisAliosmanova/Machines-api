package com.machines.machines_api.services.impl;

import com.google.gson.JsonSyntaxException;
import com.machines.machines_api.config.StripeConfig;
import com.machines.machines_api.exceptions.common.BadRequestException;
import com.machines.machines_api.exceptions.common.InternalServerErrorException;
import com.machines.machines_api.models.dto.metadata.OfferMetadata;
import com.machines.machines_api.services.WebhookService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WebhookServiceImpl implements WebhookService {
    private final StripeConfig stripeConfig;
    private final OfferServiceImpl offerService;

    @Override
    public Event constructEvent(String payload, String sigHeader) {
        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, stripeConfig.getWebhookSecret());
        } catch (JsonSyntaxException e) {
            throw new BadRequestException("Failed payload verification");
        } catch (SignatureVerificationException e) {
            throw new BadRequestException("Failed signature verification");
        }

        return event;
    }

    @Override
    public StripeObject convertStripeObject(Event event) {
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject;

        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            throw new IllegalStateException(
                    String.format("Unable to deserialize event data object for %s", event));
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }

        return stripeObject;
    }

    @Override
    public void handleStripeEvent(Event event, StripeObject stripeObject) {
        if (event.getType().equals("payment_intent.succeeded")) {
            PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
            handlePaymentSucceeded(event, paymentIntent);
            return;
        }

        throw new InternalServerErrorException("Unexpected event type");
    }

    public void handlePaymentSucceeded(Event event, PaymentIntent paymentIntent) {
        var metadata = paymentIntent.getMetadata();
        
        if (OfferMetadata.isOfferMetadata(metadata)) {
            handleOfferPromotePaymentSucceeded(event, paymentIntent);
        }
    }

    public void handleOfferPromotePaymentSucceeded(Event event, PaymentIntent paymentIntent) {
        OfferMetadata metadata = (OfferMetadata) paymentIntent.getMetadata();
        offerService.updateOfferType(metadata.getOfferId(), metadata.getOfferType());
    }
}
