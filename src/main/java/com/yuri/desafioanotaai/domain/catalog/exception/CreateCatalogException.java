package com.yuri.desafioanotaai.domain.catalog.exception;

import com.yuri.desafioanotaai.handlers.HttpException;
import org.springframework.http.HttpStatus;

public class CreateCatalogException extends RuntimeException implements HttpException {

    private final String ownerId;

    public CreateCatalogException(final String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String getErrorMessage() {
        return "";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public Object[] getErrorMessageParams() {
        return new Object[]{this.ownerId};
    }
}
