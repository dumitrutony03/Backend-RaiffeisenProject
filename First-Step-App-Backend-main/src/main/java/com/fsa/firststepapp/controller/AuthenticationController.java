package com.fsa.firststepapp.controller;

import com.fsa.firststepapp.models.Role;
import com.fsa.firststepapp.models.User;
import com.fsa.firststepapp.models.dto.UserDto;
import com.fsa.firststepapp.models.request.AuthenticationRequest;
import com.fsa.firststepapp.models.request.RegisterRequest;
import com.fsa.firststepapp.models.response.AuthenticationResponse;
import com.fsa.firststepapp.service.auth_service.AuthenticationService;
import com.fsa.firststepapp.service.auth_service.IAuthenticationService;
import com.fsa.firststepapp.service.user_service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Controlerul pentru gestionarea operațiunilor de autentificare și înregistrare.
 */
@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IUserService userService;
    private final IAuthenticationService authenticationService;
    /**
     * Endpoint pentru autentificare.
     *
     * @param request Obiectul de tip AuthenticationRequest care conține informațiile necesare pentru autentificare.
     * @return ResponseEntity cu un obiect de tip AuthenticationResponse.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        try{
            // Asta este un token, generat dupa authentificare
            var response = authenticationService.authenticate(request);

            if(response.getErrorMessage() != null)
                return ResponseEntity.badRequest().body(response);

            // Return the token to the UI + ROLE
            Optional<User> user = userService.findUserByEmail(request.getEmail());
            Role role = user.get().getRole();
            response.setRole(role);

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
}
