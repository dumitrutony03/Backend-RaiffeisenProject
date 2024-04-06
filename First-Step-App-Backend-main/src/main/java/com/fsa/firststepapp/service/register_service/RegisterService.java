package com.fsa.firststepapp.service.register_service;

import com.fsa.firststepapp.models.Role;
import com.fsa.firststepapp.models.User;
import com.fsa.firststepapp.models.request.RegisterRequest;
import com.fsa.firststepapp.models.response.AuthenticationResponse;
import com.fsa.firststepapp.repository.UserRepository;
import com.fsa.firststepapp.service.jwt_service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService implements IRegisterService {
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

        // ROLUL PENTRU OBIECTUL INVESTOR / STARTUP, VA FI OBTINUT DIN
        // UI, REGISTER PAGE
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
