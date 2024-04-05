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
        Register <=> SignUp method
     */
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if (request.getName().isEmpty())
            return AuthenticationResponse.builder()
                    .errorMessage("Name cannot be empty inside the request body!").build();

        if (request.getEmail().isEmpty())
            return AuthenticationResponse.builder()
                    .errorMessage("Email cannot be empty inside the request body!").build();

        if (request.getPassword().isEmpty())
            return AuthenticationResponse.builder()
                    .errorMessage("Password cannot be empty inside the request body!").build();

        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            System.out.println(userRepository.findUserByEmail(request.getEmail()).get() + " That email is already in use!");
            throw new DuplicateKeyException("That email is already in use!");
        }

        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .build();

        // Daca nu punem niciun ROL, atunci setam sa fie ADMIN cand inregistram un USER
        // If we do not set a ROLE, then we default set it to be ADMIN
        if (request.getRole() != null) {
            user.setRole(Role.ADMIN);
        }

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

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

        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow();

        System.out.println("useeeeeeeeeeeeeeeeeeerr: " + user.toString());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password!");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var jwtToken = jwtService.generateToken(user);

        // Asta trimitem in UI ca sa stim ce drepturi avem in functie de ce fel de LOGARE s-a facut

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
