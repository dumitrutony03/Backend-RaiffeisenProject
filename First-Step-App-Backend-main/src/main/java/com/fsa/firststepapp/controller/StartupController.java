package com.fsa.firststepapp.controller;

import com.fsa.firststepapp.models.dto.*;
import com.fsa.firststepapp.models.request.*;
import com.fsa.firststepapp.models.response.AuthenticationResponse;
import com.fsa.firststepapp.service.register_service.IRegisterService;
import com.fsa.firststepapp.service.user_service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Controlerul pentru operațiunile specifice administratorului.
 * <p>
 * FIRST ---- Ne LOGAM - altfel, inseamna ca NU AVEM CONT ----->>> ADMINUL ADAUGA SI STARTUP-UL SI INVESTOR-UL
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/startup")
public class StartupController {
    private final IUserService userService;
    private final IRegisterService registerService;
    public StartupController(IUserService userService, IRegisterService registerService) {
        this.userService = userService;
        this.registerService = registerService;
    }

    /**
     * Endpoint pentru obținerea tuturor utilizatorilor.
     *
     * @return Lista de obiecte UserDto.
     */

    @GetMapping("/allStartupsAndInvestors")
    @PreAuthorize("hasAuthority('STARTUP')")
    public ResponseEntity<List<UserDto>> getAllStartupsInvestors() {
        List<UserDto> usersList = userService.getAllUsers().stream()
                .filter(user -> "INVESTOR".equals(user.getRole()) || "STARTUP".equals(user.getRole()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(usersList);
    }
}
