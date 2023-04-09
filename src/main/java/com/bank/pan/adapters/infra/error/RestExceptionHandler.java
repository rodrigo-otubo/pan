package com.bank.pan.adapters.infra.error;

import com.bank.pan.adapters.infra.error.exceptions.BadRequestException;
import com.bank.pan.adapters.infra.error.exceptions.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException bre){
        var badRequestExceptionDetails = BadRequestExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request Exception, check the documentation")
                .details(bre.getMessage())
                .developerMessage(bre.getClass().getName())
                .build();
        return new ResponseEntity<>(badRequestExceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
