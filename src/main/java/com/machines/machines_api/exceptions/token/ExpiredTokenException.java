package com.machines.machines_api.exceptions.token;

import com.machines.machines_api.exceptions.common.UnauthorizedException;

public class ExpiredTokenException extends UnauthorizedException {
    public ExpiredTokenException() {
        super("Токенът е изтекъл!");
    }
}
