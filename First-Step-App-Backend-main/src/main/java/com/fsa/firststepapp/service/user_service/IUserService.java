package com.fsa.firststepapp.service.user_service;

import com.fsa.firststepapp.models.dto.InvestorDto;
import com.fsa.firststepapp.models.dto.UserDto;

import java.util.List;

/**
 * Interfață ce definește operațiile disponibile pentru gestionarea utilizatorilor în cadrul aplicației.
 */
public interface IUserService {

    /**
     * Obține toți utilizatorii disponibili.
     *
     * @return Lista de obiecte UserDto reprezentând utilizatorii.
     */
    List<UserDto> getAllUsers();
}

