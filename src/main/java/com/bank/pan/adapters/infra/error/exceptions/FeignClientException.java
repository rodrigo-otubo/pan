package com.bank.pan.adapters.infra.error.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FeignClientException extends RuntimeException {
    public FeignClientException(String message){
        super(message);
    }
}
