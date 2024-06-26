package com.fsa.firststepapp.service.auth_service;

import com.fsa.firststepapp.models.request.AuthenticationRequest;
import com.fsa.firststepapp.models.request.RegisterRequest;
import com.fsa.firststepapp.models.response.AuthenticationResponse;

/**
 * Interfață ce definește operația disponibile pentru serviciul de autentificare în cadrul aplicației.
 */
public interface IAuthenticationService {

    /**
     * Autentifică un utilizator în sistem.
     *
     * @param request Obiectul AuthenticationRequest ce conține informațiile necesare pentru autentificare.
     * @return Obiect AuthenticationResponse ce conține rezultatul operației și, dacă este cazul, un token de autentificare.
     */
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
