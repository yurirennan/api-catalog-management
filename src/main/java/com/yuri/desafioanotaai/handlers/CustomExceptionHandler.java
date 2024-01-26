package com.yuri.desafioanotaai.handlers;

import com.yuri.desafioanotaai.domain.catalog.exception.CatalogNotFoundException;
import com.yuri.desafioanotaai.domain.catalog.exception.CreateCatalogException;
import com.yuri.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.yuri.desafioanotaai.domain.products.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public CustomExceptionHandler(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private String retrieveErrorMessage(final String errorMessageCode, final Object[] params) {
        return this.messageSource.getMessage(
                errorMessageCode,
                params,
                LocaleContextHolder.getLocale()
        );
    }

    @ExceptionHandler({
            CategoryNotFoundException.class,
            ProductNotFoundException.class,
            CatalogNotFoundException.class,
            CreateCatalogException.class
    })
    public ResponseEntity<ApiError> handlerHttpException(final HttpException ex) {
        final String messageError = this.retrieveErrorMessage(ex.getErrorMessage(), ex.getErrorMessageParams());

        final ApiError apiError = ApiError.of(ex.getHttpStatus(), ex.getErrorMessage(), messageError);

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

}
