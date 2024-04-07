package com.fsa.firststepapp.controller;

import com.fsa.firststepapp.models.dto.*;
import com.fsa.firststepapp.models.mappers.InvestorMapper;
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
//    private final EmailService emailService;
    private final IJwtService jwtService;
    private final InvestorMapper investorMapper;

    public StartupController(IUserService userService, IJwtService jwtService, InvestorMapper investorMapper) {
        this.userService = userService;
//        this.emailService = emailService;
        this.jwtService = jwtService;
        this.investorMapper = investorMapper;
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

        // BEST PRACTICE: Ar trebui o metoda findUserByRole() implementata in UserRepository
        //              : SAU -> findInvestorByRole() in InvestorRepositry


        List<UserDto> investorsList = new ArrayList<>();

        for (UserDto userDto : userService.getAllUsers()) {
            if (userDto.getRole().equals("INVESTOR")) {
                investorsList.add(userDto);
            }
        }
        return ResponseEntity.ok(investorsList);
    }

    @PostMapping("/sendMentoringEmailToInvestor")
    @PreAuthorize("hasAnyAuthority('STARTUP','ADMIN')") // <-------- to be implemented in front end for AdminPage
    public ResponseEntity<String> testt(@RequestBody InvestorReceived investorReceived) {
//        System.out.println("Token: " + investorReceived.getToken());
//        System.out.println(investorReceived.getName() + " " + investorReceived.getEmail());
        String fromEmail = "snakesfifty@gmail.com";//jwtService.extractUsername(investorReceived.getToken());
        String toEmail = "snakesfifty@gmail.com";//investorReceived.getEmail();
        String emailSubject = "Subject";
        String emailText = "Body";

        /*System.out.println("fromEmail: " + fromEmail);
        System.out.println("toEmail: " + toEmail);
        System.out.println("emailSubject: " + emailSubject);
        System.out.println("emailText: " + emailText);*/


        System.setProperty("java.net.preferIPv4Stack", "true");
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");

        String host = "smtp.gmail.com";
        int port = 587;
        String userName = "xxx";
        String password = "xxx";

        String mailTo = "xx@gmail.com";
        String subject = "mail content";



        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setJavaMailProperties(props);
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(userName);
        sender.setPassword(password);



        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(mailTo);
            helper.setSubject(subject);
            helper.setText("test test");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        sender.send(message);

        return ResponseEntity.ok("test");
    }

//        emailService.sendEmail(fromEmail, toEmail, emailSubject, emailText);

//        return ResponseEntity.ok("primitt");
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
