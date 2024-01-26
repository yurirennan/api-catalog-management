package com.yuri.desafioanotaai.handlers;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Data
public class ApiError {

    private HttpStatus status;
    private List<String> errors;
    private String message;

    public static ApiError of(final HttpStatus status,
                              final String error,
                              final String errorMessage) {
        final ApiError apiError = new ApiError();

        apiError.setStatus(status);
        apiError.setErrors(Collections.singletonList(error));
        apiError.setMessage(errorMessage);

        return apiError;
    }

    public static ApiError of(final HttpStatus status,
                              final List<String> errors,
                              final String errorMessage) {
        final ApiError apiError = new ApiError();

        apiError.setStatus(status);
        apiError.setErrors(errors);
        apiError.setMessage(errorMessage);

        return apiError;
    }
}
