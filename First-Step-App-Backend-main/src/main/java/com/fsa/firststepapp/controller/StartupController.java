package com.fsa.firststepapp.controller;

import com.fsa.firststepapp.models.dto.*;
import com.fsa.firststepapp.models.mappers.InvestorMapper;
import com.fsa.firststepapp.service.email_service.EmailService;
import com.fsa.firststepapp.service.implementation.EmailServiceImpl;
import com.fsa.firststepapp.service.jwt_service.IJwtService;
import com.fsa.firststepapp.service.user_service.IUserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Controlerul pentru operațiunile specifice STARTUP-ULUI.
 * Venim cu prezumtia ca ADMIN-UL ne-a inregistrat pe platforma.
 * ( Best practice ar fi fost fost: )
 * Pasul 1: Startup-ul trimite o cerere ( completata pe platforma ) cu datele sale personale,
 * cu proiectul, obiectivele proiectului dezvoltat ( sau in curs de dezvoltare )
 * <p>
 * Pasul 2: ADMINII autorizeaza aceasta cerere, si ii atribuie un cont pe platforma ( Il adaugam in DB ),
 * urmand ca STARTUP-UL respectiv sa primeasca tot un EMAIL cu credentialele pentru logare ex: Email si Parola.
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/startups")
public class StartupController {
    private final IUserService userService;
    private final IJwtService jwtService;
    private final InvestorMapper investorMapper;
    private final EmailServiceImpl emailService;
    public StartupController(IUserService userService, IJwtService jwtService, InvestorMapper investorMapper, EmailServiceImpl emailService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.investorMapper = investorMapper;
        this.emailService = emailService;
    }

//    @GetMapping("/startupDetails")
//    @PreAuthorize("hasAnyAuthority('STARTUP', 'ADMIN')") // <--- PENTRU ADMIN TREBUIE SA SELECTAM UN STARTUP
//    public ResponseEntity<Optional<User>> startupDetails(@RequestBody String token) {
//        System.out.println(token);
//
//        String email = jwtService.extractUsername(token);
//        Optional<User> user = userService.findUserByEmail(email);
//
//        if (user.isPresent())
//            return ResponseEntity.ok(user);
//        else
//            return null;
//
//    }

    /**
     * Endpoint pentru obținerea tuturor utilizatorilor.
     *
     * @return Lista de obiecte StartupDto.
     */
    // O lista publica cu toti investitorii
    @PostMapping("/allInvestors")
    @PreAuthorize("hasAnyAuthority('STARTUP','ADMIN')") // <-------- to be implemented in front end for AdminPage
    public ResponseEntity<List<UserDto>> getAllnvestors(@RequestBody String token) {
        List<UserDto> investorsList = new ArrayList<>();

        for (UserDto userDto : userService.getAllUsers()) {
            if (userDto.getRole().equals("INVESTOR")) {
                investorsList.add(userDto);
            }
        }
        return ResponseEntity.ok(investorsList);
    }

    @PostMapping("/s")
    @PreAuthorize("hasAnyAuthority('STARTUP','ADMIN')") // <-------- to be implemented in front end for AdminPage
    public ResponseEntity<String> b(@RequestBody InvestorReceived investorReceived) {
        System.out.println("Email Startup care trimite MAIL spre Investor: " + jwtService.extractUsername(investorReceived.getToken()));
        System.out.println(investorReceived.getName() + " " + investorReceived.getEmail());

        String fromEmail = jwtService.extractUsername(investorReceived.getToken());
        String toEmail = investorReceived.getEmail();
        String emailSubject = "Legatura Startup -> Investor";
        String emailText = "Salutare! Folosesti platforma noastra cu scopul de a iti spori sansele pentru a primi o finantare cel putin buna!";

        // OBLIGATORIU PE HOTSPOT CONECTAREA => ERORI DE TIMEOUT!

        // to: "startupMailAdress@gmail.com" -> adresa Startup-ului,
        // subject: "investorChoosen@gmail.com" -> adresa Investitor-ului
        // text:
        emailService.sendSimpleMessage("dumitruantoniodaniel03@gmail.com", "Legatura Startup -> Investor", "Salutare! Folosesti platforma noastra cu scopul de a iti spori sansele pentru a primi o finantare cel putin buna!");

        // #### Investorul primeste email-ul Startup-ului, iar daca este de acord cu:
        // propunerea sa, cu planul sau de afaceri ( in format .PDF ), furnizat in acest email
        // Acesta poate trimite un REPLY Startup-ului cu un link de Teams, care propune o sedinta 1 la 1, la un moment dat.

        return ResponseEntity.ok(investorReceived.getToken());
    }
}

/**
 * Clasa care reprezintă obiectul de cerere pentru obiectul primit prin Requestul de alegere a investitorului de catre startup.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class InvestorReceived {
    private String token;
    private String name;
    private String email;
}
