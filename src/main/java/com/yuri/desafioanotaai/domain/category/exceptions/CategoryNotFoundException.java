package com.yuri.desafioanotaai.domain.category.exceptions;

import com.yuri.desafioanotaai.handlers.HttpException;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CategoryNotFoundException extends RuntimeException implements HttpException {

    private final String categoryId;

    public CategoryNotFoundException(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String getErrorMessage() {
        return "errors.category.not.found";
    }
    @Override
    public Object[] getErrorMessageParams() {
        return new String[]{categoryId};
    }
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
