//package com.fsa.firststepapp.controller;
//
//import com.fsa.firststepapp.models.dto.InvestorDto;
//import com.fsa.firststepapp.models.dto.UserDto;
//import com.fsa.firststepapp.service.auth_service.AuthenticationService;
//import com.fsa.firststepapp.service.auth_service.IAuthenticationService;
//import com.fsa.firststepapp.service.user_service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
///**
// * Controlerul pentru gestionarea utilizatorilor.
// */
//@RestController
//@CrossOrigin(origins="*", allowedHeaders = "*")
//@RequestMapping("/api/users")
//public class UserController {
//    private final IUserService userService;
//    private final IAuthenticationService authenticationService;
//    @Autowired
//    public UserController(IUserService userService, IAuthenticationService authenticationService) {
//        this.userService = userService;
//        this.authenticationService = authenticationService;
//    }
//
//    /**
//     * Endpoint pentru obținerea tuturor utilizatorilor.
//     *
//     * @return Lista de obiecte UserDto.
//     */
//    @GetMapping("")
//    public List<UserDto> getAllUsers() {
//        return userService.getAllUsers();
//    }
//}
