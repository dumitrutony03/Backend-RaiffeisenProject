package com.fsa.firststepapp.controller;

import com.fsa.firststepapp.models.User;
import com.fsa.firststepapp.models.dto.*;
import com.fsa.firststepapp.models.mappers.InvestorMapper;
import com.fsa.firststepapp.service.jwt_service.IJwtService;
import com.fsa.firststepapp.service.user_service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public StartupController(IUserService userService, IJwtService jwtService, InvestorMapper investorMapper) {
        this.userService = userService;
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

        for (UserDto userDto : userService.getAllUsers()){
            if (userDto.getRole().equals("INVESTOR")) {
                investorsList.add(userDto);
            }
        }
        return ResponseEntity.ok(investorsList);
    }
    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('STARTUP','ADMIN')") // <-------- to be implemented in front end for AdminPage
    public String testt(@RequestBody String token){
        return token;
    }

}
