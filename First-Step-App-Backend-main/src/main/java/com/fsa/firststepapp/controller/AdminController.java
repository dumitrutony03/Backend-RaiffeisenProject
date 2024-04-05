package com.fsa.firststepapp.controller;

import com.fsa.firststepapp.models.dto.*;
import com.fsa.firststepapp.models.request.*;
import com.fsa.firststepapp.models.response.DeleteResponse;
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
//    private final IAnnouncementService announcementService;
//    private final IUniversityService universityService;
//    private final ILocationService locationService;
//    private final IEventService eventService;
//    private final IFacultyService facultyService;
//
//    @Autowired
//    public AdminController(IEventService eventService) {
//        this.eventService = eventService;
//    }

    /**
     * Endpoint pentru obținerea tuturor evenimentelor
     *
     * @return Lista de obiecte EventDto.
     */
//    @GetMapping("/events")
//    public List<EventDto> getAllEvents() {
//        return eventService.getAllEvents();
//    }
//
//    /**
//     * Endpoint pentru adăugarea unui eveniment nou
//     *
//     * @param event Obiectul de tip AddEventRequest care conține informațiile necesare pentru adăugarea evenimentului.
//     * @return obiectul EventDto adaugat
//     */
//    @PostMapping("/events")
//    public EventDto addEvent(@RequestBody AddEventRequest event) {
//        return eventService.addEvent(event);
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
//
//    /**
//     * Endpoint pentru ștergerea unui eveniment
//     *
//     * @param eventId ID-ul evenimentului pe care vrem sa il stergem.
//     */
//    @DeleteMapping("/events/{eventId}")
//    public DeleteResponse deleteEvent(@PathVariable String eventId) {
//        eventService.deleteEvent(eventId);
//
//        return new DeleteResponse("Event deleted successfully!!");
//    }
}
