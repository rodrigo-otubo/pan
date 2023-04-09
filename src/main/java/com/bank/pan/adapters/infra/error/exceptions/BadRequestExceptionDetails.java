package com.bank.pan.adapters.infra.error.exceptions;

import com.bank.pan.adapters.infra.error.ExceptionDetails;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class BadRequestExceptionDetails extends ExceptionDetails {
}
