package com.smyunis.halite.web.exceptionhandlers;

import com.smyunis.halite.application.applicationexceptions.EntityNotFoundException;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEntityNotFoundException() {
    }

    @ExceptionHandler(InvalidValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleInvalidValueException() {
    }

    @ExceptionHandler(InvalidOperationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleInvalidOperationException() {
    }


    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleAllExceptions(Throwable ex) {
    }

}
