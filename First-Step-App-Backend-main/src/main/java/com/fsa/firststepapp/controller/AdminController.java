package com.fsa.firststepapp.controller;

import com.fsa.firststepapp.models.User;
import com.fsa.firststepapp.models.dto.*;
import com.fsa.firststepapp.models.request.*;
import com.fsa.firststepapp.models.response.AuthenticationResponse;
import com.fsa.firststepapp.service.auth_service.IAuthenticationService;
import com.fsa.firststepapp.service.register_service.IRegisterService;
import com.fsa.firststepapp.service.user_service.IUserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/admin")
public class AdminController {
    private final IUserService userService;
    private final IRegisterService registerService;

    @Autowired
    public AdminController(IUserService userService, IRegisterService registerService) {
        this.userService = userService;
        this.registerService = registerService;
    }

    /**
     * Endpoint pentru înregistrare.
     *
     * @param request Obiectul de tip RegisterRequest care conține informațiile necesare pentru înregistrare.
     * @return ResponseEntity cu un obiect de tip AuthenticationResponse.
     */
    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        System.out.println("Ceva ce incearca sa fie inregistrat: " + request.getName() + " " + request.getEmail() + " " + request.getPassword() + " " + request.getPassword());
        try {
            var response = this.registerService.register(request);

            System.out.println("Raspuns din pagina de inregistrare INVESTOR/STARTUP: " + response);

            if (response.getErrorMessage() != null)
                return ResponseEntity.badRequest().body(response);

            return ResponseEntity.ok(response);
        } catch (NoSuchElementException noSuchElementException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AuthenticationResponse.builder()
                    .errorMessage("No entity with that name was found!(University or Faculty)").build());
        } catch (DuplicateKeyException duplicateKeyException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(AuthenticationResponse.builder()
                    .errorMessage("Email is already in use!").build());
        }
    }


    /**
     * Endpoint pentru obținerea tuturor utilizatorilor.
     *
     * @return Lista de obiecte UserDto.
     */

    // TO BE IMPLEMENTED! <---- IN FRONT-END
    @GetMapping("/allStartupsAndInvestors")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllStartupsInvestors() {
        List<UserDto> usersList = userService.getAllUsers().stream()
                .filter(user -> "INVESTOR".equals(user.getRole()) || "STARTUP".equals(user.getRole()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(usersList);
    }
}
