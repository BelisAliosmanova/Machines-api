package com.machines.machines_api.schedulers;

import com.machines.machines_api.enums.ExceptionSeverity;
import com.machines.machines_api.models.entity.Exception;
import com.machines.machines_api.repositories.ExceptionRepository;
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
public class ExceptionScheduler {
    private final ExceptionRepository exceptionRepository;

    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void handleExceptionLogRotation() {
        List<Exception> exceptions = exceptionRepository.findAllBySeverityIs(ExceptionSeverity.INFORMATIONAL);

        for (Exception exception : exceptions) {
            LocalDateTime now = LocalDateTime.now();
            Instant createdAtInstant = exception.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant();
            Instant nowInstant = now.atZone(ZoneId.systemDefault()).toInstant();
            Duration durationFromCreate = Duration.between(createdAtInstant, nowInstant);

            if (durationFromCreate.toDays() >= 1) {
                exceptionRepository.delete(exception);
            }
        }
    }
}
