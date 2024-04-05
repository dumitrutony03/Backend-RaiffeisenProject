package com.fsa.firststepapp.service.jwt_service;

/**
 * Interfață de serviciu pentru manipularea token-urilor JWT.
 */
public interface IJwtService {

    /**
     * Extrage numele de utilizator dintr-un token JWT.
     *
     * @param token Tokenul JWT din care se extrage numele de utilizator.
     * @return Numele de utilizator extras din tokenul JWT.
     */
    String extractUsername(String token);
}
