package com.fsa.firststepapp.service.register_service;

import com.fsa.firststepapp.models.request.RegisterRequest;
import com.fsa.firststepapp.models.response.AuthenticationResponse;

/**
 * Interfață ce definește operația disponibile pentru serviciul de autentificare în cadrul aplicației.
 */
public interface IRegisterService {

    /**
     * Înregistrează un nou utilizator în sistem.
     *
     * @param request Obiectul RegisterRequest ce conține informațiile pentru înregistrarea utilizatorului.
     * @return Obiect AuthenticationResponse ce conține rezultatul operației și, dacă este cazul, un token de autentificare.
     */
    AuthenticationResponse register(RegisterRequest request);

}
