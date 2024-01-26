package com.yuri.desafioanotaai.handlers;

import org.springframework.http.HttpStatus;

public interface HttpException {

    public String getErrorMessage();

    public HttpStatus getHttpStatus();

    public Object[] getErrorMessageParams();

}
