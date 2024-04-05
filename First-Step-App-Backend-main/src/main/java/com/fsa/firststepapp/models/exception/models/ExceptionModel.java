package com.fsa.firststepapp.models.exception.models;

/**
 * Model utilizat pentru a reprezenta informațiile despre o excepție în cadrul unei răspunsuri HTTP.
 */
public class ExceptionModel {
    private final Integer statusCode;
    private final String message;

    public ExceptionModel(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * Returnează codul de status HTTP.
     *
     * @return Codul de status HTTP asociat excepției.
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * Returnează mesajul de eroare.
     *
     * @return Mesajul de eroare asociat excepției.
     */
    public String getMessage() {
        return message;
    }
}
