package com.fsa.firststepapp.controller;

import com.fsa.firststepapp.models.request.AuthenticationRequest;
import com.fsa.firststepapp.models.request.RegisterRequest;
import com.fsa.firststepapp.models.response.AuthenticationResponse;
import com.fsa.firststepapp.service.auth_service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * Controlerul pentru gestionarea operațiunilor de autentificare și înregistrare.
 */
@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    /**
     * Endpoint pentru înregistrare.
     *
     * @param request Obiectul de tip RegisterRequest care conține informațiile necesare pentru înregistrare.
     * @return ResponseEntity cu un obiect de tip AuthenticationResponse.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        try{
            var response = this.authenticationService.register(request);

            if(response.getErrorMessage() != null)
                return ResponseEntity.badRequest().body(response);

            return ResponseEntity.ok(response);
        }
        catch (NoSuchElementException noSuchElementException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AuthenticationResponse.builder()
                    .errorMessage("No entity with that name was found!(University or Faculty)").build());
        }
        catch (DuplicateKeyException duplicateKeyException){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(AuthenticationResponse.builder()
                    .errorMessage("Email is already in use!").build());
        }
    }

    /**
     * Endpoint pentru autentificare.
     *
     * @param request Obiectul de tip AuthenticationRequest care conține informațiile necesare pentru autentificare.
     * @return ResponseEntity cu un obiect de tip AuthenticationResponse.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        try{
            var response = authenticationService.authenticate(request);

            if(response.getErrorMessage() != null)
                return ResponseEntity.badRequest().body(response);

            return ResponseEntity.ok(response);
        }
        catch (NoSuchElementException noSuchElementException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AuthenticationResponse.builder()
                    .errorMessage("No user with that email was found!").build());
        }
        catch (BadCredentialsException badCredentialsException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthenticationResponse.builder()
                    .errorMessage("Invalid password!").build());
        }
    }

    @PostMapping("/test-req")
    public ResponseEntity<String> testPost(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok("POST SUCCESS!");
    }
}
