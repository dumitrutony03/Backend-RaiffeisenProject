package com.fsa.firststepapp.controller;

import com.fsa.firststepapp.models.User;
import com.fsa.firststepapp.models.dto.InvestorDto;
import com.fsa.firststepapp.models.response.AuthenticationResponse;
import com.fsa.firststepapp.service.jwt_service.IJwtService;
import com.fsa.firststepapp.service.user_service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Controlerul pentru gestionarea investitorilor.
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/investors")
public class InvestorController {
    private final IUserService userService;
    private final IJwtService jwtService;

    public InvestorController(IUserService userService, IJwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

//    @GetMapping("/investorDetails")
    @PostMapping("/investorDetails")
    @PreAuthorize("hasAnyAuthority('INVESTOR', 'ADMIN')")
    public ResponseEntity<Optional<User>> investorDetails(@RequestBody String token) {
        System.out.println(token);

        String email = jwtService.extractUsername(token);
        Optional<User> user = userService.findUserByEmail(email);

        if (user.isPresent())
            return ResponseEntity.ok(user);
        else
            return null;

    }
}
