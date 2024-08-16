package com.machines.machines_api.config.schedulers;

import com.machines.machines_api.models.entity.Advertisement;
import com.machines.machines_api.repositories.AdvertisementRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@ComponentScan
@Component
@EnableScheduling
public class AdvertisementScheduler {

    private final AdvertisementRepository advertisementRepository;

    public AdvertisementScheduler(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }


    @Scheduled(cron = "0 00 0 * * *") // Run every 24 hours
    public void deleteUnconfirmedUsers() {
        List<Advertisement> advertisements = advertisementRepository.findAllByDeletedAtIsNull();

        for (Advertisement advertisement : advertisements) {
            if (Objects.equals(advertisement.getEndDate(), LocalDate.now())) {
                advertisement.setActive(false);
                advertisement.setDeletedAt(LocalDateTime.now());
            }
        }
    }
}
