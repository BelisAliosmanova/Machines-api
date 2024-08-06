package com.machines.machines_api.schedulers;

import com.machines.machines_api.models.entity.Offer;
import com.machines.machines_api.repositories.OfferRepository;
import com.machines.machines_api.services.OfferService;
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
import java.util.TimeZone;

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
            Duration duration = Duration.between(createdAtInstant, nowInstant);

            if (offer.getRenewedAt() == null && duration.toDays() >= 30) {
                offer.setRenewedAt(now);
            }
        }

        offerRepository.saveAll(offers);
    }
}
