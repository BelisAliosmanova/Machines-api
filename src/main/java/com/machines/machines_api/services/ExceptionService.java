package com.machines.machines_api.services;

import com.machines.machines_api.exceptions.common.ApiException;

public interface ExceptionService {

    void log(ApiException runtimeException);

    void log(RuntimeException runtimeException, int statusCode);
}
