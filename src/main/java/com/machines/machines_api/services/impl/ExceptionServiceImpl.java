package com.machines.machines_api.services.impl;

import com.machines.machines_api.exceptions.common.ApiException;
import com.machines.machines_api.models.entity.Exception;
import com.machines.machines_api.repositories.ExceptionRepository;
import com.machines.machines_api.services.ExceptionService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@AllArgsConstructor
public class ExceptionServiceImpl implements ExceptionService {
    private final ExceptionRepository exceptionRepository;

    @Override
    @Async
    public void log(ApiException apiException) {
        Exception exception = Exception.mapFromApiException(apiException);
        exceptionRepository.save(exception);
    }

    @Override
    @Async
    public void log(RuntimeException runtimeException, int statusCode) {
        Exception exception = Exception.mapFromRuntimeException(runtimeException, statusCode);
        exceptionRepository.save(exception);
    }
}
