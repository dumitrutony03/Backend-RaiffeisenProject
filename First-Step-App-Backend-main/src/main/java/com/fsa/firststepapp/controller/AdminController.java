package com.fsa.firststepapp.controller;

import com.fsa.firststepapp.models.dto.*;
import com.fsa.firststepapp.models.request.*;
import com.fsa.firststepapp.service.user_service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlerul pentru operațiunile specifice administratorului.
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/admin")
public class AdminController {
    private final IUserService userService;

    @Autowired
    public AdminController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint pentru obținerea tuturor evenimentelor
     *
     * @return Lista de obiecte UserDto.
     */
    @GetMapping("/allUsers")
    public List<UserDto> getAllInvestors() {
        return userService.getAllUsers();
    }

    /**
     * Endpoint pentru adăugarea unui eveniment nou
     *
     * @param registerRequest Obiectul de tip RegisterRequest care conține informațiile necesare pentru adăugarea evenimentului.
     * @return obiectul InvestorDto adaugat
     */
//    @PostMapping("/investors")
//    public InvestorDto addEvent(@RequestBody RegisterRequest registerRequest) {
//        return userService.addUser(registerRequest);
//    }
//
//    /**
//     * Endpoint pentru actualizarea unui eveniment existente
//     *
//     * @param event Obiectul de tip AddEventRequest care conține informațiile necesare pentru actualizarea evenimentului.
//     * @param eventId ID-ul evenimentului pe care vrem sa il actualizam.
//     */
//    @PutMapping("/events/{eventId}")
//    public EventDto updateEvent(@PathVariable String eventId, @RequestBody AddEventRequest event) {
//        return eventService.updateEvent(eventId, event);
//    }
}
