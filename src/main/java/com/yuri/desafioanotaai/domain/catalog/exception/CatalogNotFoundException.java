package com.yuri.desafioanotaai.domain.catalog.exception;

import com.yuri.desafioanotaai.handlers.HttpException;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CatalogNotFoundException extends RuntimeException implements HttpException {

    private final String ownerId;

    @Override
    public String getErrorMessage() {
        return "errors.catalog.not.found";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public Object[] getErrorMessageParams() {
        return new Object[]{this.ownerId};
    }
}
