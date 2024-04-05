package com.fsa.firststepapp.models.exception.models;

/**
 * Excepție aruncată atunci când o entitate nu este găsită.
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
