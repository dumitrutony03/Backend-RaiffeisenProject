package com.fsa.firststepapp.models.exception;

import com.fsa.firststepapp.models.exception.models.ExceptionModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clasă care gestionează excepțiile globale ale aplicației.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Metoda care gestionează excepțiile de tip EntityNotFoundException.
     *
     * @param exception Excepția EntityNotFoundException.
     * @return Un obiect ExceptionModel pentru a fi inclus în răspunsul HTTP.
     */
    @ExceptionHandler(value= EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionModel handleEntityNotFoundException(EntityNotFoundException exception) {
        return new ExceptionModel(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
