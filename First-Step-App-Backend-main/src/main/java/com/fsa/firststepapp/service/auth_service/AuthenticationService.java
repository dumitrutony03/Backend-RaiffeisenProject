package com.fsa.firststepapp.service.auth_service;

import com.fsa.firststepapp.models.Role;
import com.fsa.firststepapp.models.Admin;
import com.fsa.firststepapp.models.User;
import com.fsa.firststepapp.models.request.AuthenticationRequest;
import com.fsa.firststepapp.models.request.RegisterRequest;
import com.fsa.firststepapp.models.response.AuthenticationResponse;
import com.fsa.firststepapp.repository.UserRepository;
import com.fsa.firststepapp.service.jwt_service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements  IAuthenticationService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    /*
        Register <=> SignUp method
     */
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if(request.getName().isEmpty())
            return AuthenticationResponse.builder()
                    .errorMessage("Name cannot be empty inside the request body!").build();

        if(request.getEmail().isEmpty())
            return AuthenticationResponse.builder()
                    .errorMessage("Email cannot be empty inside the request body!").build();

        if(request.getPassword().isEmpty())
            return AuthenticationResponse.builder()
                    .errorMessage("Password cannot be empty inside the request body!").build();

       if(userRepository.findAdminByEmail(request.getEmail()))
            throw new DuplicateKeyException("That email is already in use!");

        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(Long.valueOf(passwordEncoder.encode(request.getPassword())))
                .role(Role.valueOf(request.getRole()))
                .build();

        // Daca nu punem niciun ROL, atunci setam sa fie ADMIN cand inregistram un USER
        if(request.getRole() != null) {
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
        if(request.getEmail().isEmpty())
            return AuthenticationResponse.builder()
                    .errorMessage("Email cannot be empty inside the request body!").build();

        if(request.getPassword().isEmpty())
            return AuthenticationResponse.builder()
                    .errorMessage("Password cannot be empty inside the request body!").build();

        var user = userRepository.findAdminByEmailAndPassword(request.getEmail(), Long.valueOf(request.getPassword()))
                .orElseThrow();

        if (!passwordEncoder.matches(request.getPassword(), String.valueOf(user.getPassword()))) {
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
