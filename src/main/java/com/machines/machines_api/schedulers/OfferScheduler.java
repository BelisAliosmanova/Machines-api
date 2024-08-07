package com.machines.machines_api.schedulers;

import com.machines.machines_api.enums.OfferType;
import com.machines.machines_api.models.entity.Offer;
import com.machines.machines_api.repositories.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Component
@EnableAsync
@RequiredArgsConstructor
public class OfferScheduler {
    private final OfferRepository offerRepository;

    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void handleAutoUpdateTask() {
        List<Offer> offers = offerRepository.findAllByAutoUpdateIsTrue();

        for (Offer offer : offers) {
            LocalDateTime now = LocalDateTime.now();
            Instant createdAtInstant = offer.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant();
            Instant nowInstant = now.atZone(ZoneId.systemDefault()).toInstant();
            Duration durationFromCreate = Duration.between(createdAtInstant, nowInstant);

            if (offer.getRenewedAt() == null && durationFromCreate.toDays() >= 30) {
                offer.setRenewedAt(now);
            } else if (offer.getRenewedAt() != null) {
                Instant renewedAtInstant = offer.getRenewedAt().atZone(ZoneId.systemDefault()).toInstant();
                Duration durationFromLastRenew = Duration.between(renewedAtInstant, nowInstant);

                if (durationFromLastRenew.toDays() >= 30) {
                    offer.setRenewedAt(now);
                }
            }
        }

        offerRepository.saveAll(offers);
    }

    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void handlePromoteOfferTypeExpire() {
        List<Offer> offers = offerRepository.findAllByOfferTypeIsNot(OfferType.getDefaultOfferType());

        for (Offer offer : offers) {
            LocalDateTime now = LocalDateTime.now();

            LocalDateTime dateTime = offer.getPromotedAt();

            if (dateTime == null) {
                dateTime = offer.getCreatedAt();
            }

            Instant dateTimeInstant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
            Instant nowInstant = now.atZone(ZoneId.systemDefault()).toInstant();
            Duration duration = Duration.between(dateTimeInstant, nowInstant);

            if (duration.toDays() > offer.getOfferType().getExpiresAfterDays()) {
                offer.setOfferType(OfferType.getDefaultOfferType());
            }
        }

        offerRepository.saveAll(offers);
    }

}
