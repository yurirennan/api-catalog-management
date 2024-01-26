package com.yuri.desafioanotaai.domain.products.exceptions;

import com.yuri.desafioanotaai.handlers.HttpException;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ProductNotFoundException extends RuntimeException implements HttpException {

    private final String productId;

    public ProductNotFoundException(final String productId) {
        this.productId = productId;
    }

    @Override
    public String getErrorMessage() {
        return "errors.product.not.found";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public Object[] getErrorMessageParams() {
        return new Object[]{productId};
    }
}
