package com.fsa.firststepapp.controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        try {
            var response = this.registerService.register(request);

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
     * Endpoint pentru obținerea tuturor evenimentelor
     *
     * @return Lista de obiecte UserDto.
     */
    @GetMapping("/allUsers")
    public String getAllInvestors() {
        System.out.println("Nr de useri din DB: " + userService.getAllUsers().size());
        return "(ResponseEntity<AuthenticationResponse>) userService.getAllUsers()";
    }

    /**
     * Endpoint pentru adăugarea unui eveniment nou
     *
     * @param registerRequest Obiectul de tip RegisterRequest care conține informațiile necesare pentru adăugarea evenimentului.
     * @return obiectul InvestorDto adaugat
     */
//    @PostMapping("/investors")
//    public InvestorDto addEvent(@RequestBody RegisterRequest registerRequest) {
//        return userService.addUser(registerRequest);
//    }

}
