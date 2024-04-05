package com.fsa.firststepapp.config;

import com.fsa.firststepapp.repository.AdminRepository;
import com.fsa.firststepapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configurarea aplicației Spring Security.
 */
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepository userRepository;

    /**
     * Returnează un obiect UserDetailsService care obține detalii despre utilizator în cadrul procesului de autentificare.
     *
     * @return UserDetailsService configurat pentru a obține detalii despre utilizator.
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return email -> userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Returnează un obiect AuthenticationProvider configurat pentru autentificare.
     *
     * @return AuthenticationProvider configurat pentru a utiliza un obiect UserDetailsService și un obiect PasswordEncoder.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /**
     * Returnează un obiect PasswordEncoder configurat pentru a utiliza algoritmul BCrypt pentru criptarea parolelor.
     *
     * @return PasswordEncoder configurat cu BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Returnează un obiect AuthenticationManager din configurația dată.
     *
     * @param config Configurația pentru a obține AuthenticationManager.
     * @return AuthenticationManager configurat pentru a gestiona autentificarea.
     * @throws Exception Excepție în cazul unor erori în timpul configurării.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
