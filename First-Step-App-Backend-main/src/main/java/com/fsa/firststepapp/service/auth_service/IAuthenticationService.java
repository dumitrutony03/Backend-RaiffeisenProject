package com.fsa.firststepapp.service.auth_service;

import com.fsa.firststepapp.models.request.AuthenticationRequest;
import com.fsa.firststepapp.models.request.RegisterRequest;
import com.fsa.firststepapp.models.response.AuthenticationResponse;

/**
 * Interfață ce definește operațiile disponibile pentru serviciul de autentificare în cadrul aplicației.
 */
public interface IAuthenticationService {

    /**
     * Înregistrează un nou utilizator în sistem.
     *
     * @param request Obiectul RegisterRequest ce conține informațiile pentru înregistrarea utilizatorului.
     * @return Obiect AuthenticationResponse ce conține rezultatul operației și, dacă este cazul, un token de autentificare.
     */
    AuthenticationResponse register(RegisterRequest request);

    /**
     * Autentifică un utilizator în sistem.
     *
     * @param request Obiectul AuthenticationRequest ce conține informațiile necesare pentru autentificare.
     * @return Obiect AuthenticationResponse ce conține rezultatul operației și, dacă este cazul, un token de autentificare.
     */
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
