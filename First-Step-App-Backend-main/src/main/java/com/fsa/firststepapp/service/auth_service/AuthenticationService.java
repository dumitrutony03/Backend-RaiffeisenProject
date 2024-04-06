package com.fsa.firststepapp.service.auth_service;

import com.fsa.firststepapp.models.Role;
import com.fsa.firststepapp.models.User;
import com.fsa.firststepapp.models.request.AuthenticationRequest;
import com.fsa.firststepapp.models.request.RegisterRequest;
import com.fsa.firststepapp.models.response.AuthenticationResponse;
import com.fsa.firststepapp.repository.UserRepository;
import com.fsa.firststepapp.service.jwt_service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /*
        LogIn <=> SignIn method
     */
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if (request.getEmail().isEmpty())
            return AuthenticationResponse.builder()
                    .errorMessage("Email cannot be empty inside the request body!").build();

        if (request.getPassword().isEmpty())
            return AuthenticationResponse.builder()
                    .errorMessage("Password cannot be empty inside the request body!").build();

        // gasim datele primite la AUTENTIFICARE ca fiind prezente in DB
        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow();

//        System.out.println("USER DIN AUTHENTIFICAT ACUMA IN DB, ARE detaliile:" + user.toString());;

        // user.getPassword() - parola codata din DB
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password!");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var jwtToken = jwtService.generateToken(user);

        // Asta trimitem in UI ca sa stim ce drepturi avem in functie de ce fel de LOGARE s-a facut
//        String extractedUserEmail = jwtService.extractUsername(jwtToken);
//        System.out.println(extractedUserEmail);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
