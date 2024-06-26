package com.fsa.firststepapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

/**
 * Configurația de securitate a aplicației folosind Spring Security.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    // Gandit pentru permisiuni dupa ROLUL USER-ULUI

//    private final ApplicationUserRole applicationUserRole;

    /**
     * Configurarea lanțului de securitate.
     *
     * @param httpSecurity Configurația pentru obiectul HttpSecurity.
     * @return Un obiect SecurityFilterChain configurat.
     * @throws Exception Excepție în cazul unor erori de configurare.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(GET, "/api/admin/**").hasAuthority("ADMIN") // pentru cand trimitem din UI token ul inapoi cu authorizare, si apoi validam tokenul in partea de server
                                .requestMatchers(POST, "/api/admin/**").hasAuthority("ADMIN")
                                .requestMatchers(PUT, "/api/admin/**").hasAuthority("ADMIN")
                                .requestMatchers(DELETE, "/api/admin/**").hasAuthority("ADMIN")

                                .requestMatchers(GET, "/api/investors/**").hasAnyAuthority("INVESTOR", "ADMIN") // pentru cand trimitem din UI token ul inapoi cu authorizare, si apoi validam tokenul in partea de server
                                .requestMatchers(POST, "/api/investors/**").hasAnyAuthority("INVESTOR", "ADMIN")
                                .requestMatchers(PUT, "/api/investors/**").hasAnyAuthority("INVESTOR", "ADMIN")
                                .requestMatchers(DELETE, "/api/investors/**").hasAnyAuthority("INVESTOR", "ADMIN")
//
                                .requestMatchers(GET, "/api/startups/**").hasAnyAuthority("STARTUP", "ADMIN")
                                .requestMatchers(POST, "/api/startups/**").hasAnyAuthority("STARTUP", "ADMIN")
                                .requestMatchers(PUT, "/api/startups/**").hasAnyAuthority("STARTUP", "ADMIN")
                                .requestMatchers(DELETE, "/api/startups/**").hasAnyAuthority("STARTUP", "ADMIN")

                                .requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
