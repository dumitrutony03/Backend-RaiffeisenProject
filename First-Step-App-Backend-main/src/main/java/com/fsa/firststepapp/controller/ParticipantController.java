//package com.fsa.firststepapp.controller;
//
//import com.fsa.firststepapp.models.request.AddParticipantRequest;
//import com.fsa.firststepapp.service.participant_service.IParticipantService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * Controlerul pentru gestionarea participanților.
// */
//@RestController
//@CrossOrigin(origins="*", allowedHeaders = "*")
//@RequestMapping("/api/participants")
//public class ParticipantController {
//    private final IParticipantService participantService;
//
//    @Autowired
//    public ParticipantController(IParticipantService participantService) {
//        this.participantService = participantService;
//    }
//
//    /**
//     * Endpoint pentru obținerea tuturor participanților.
//     *
//     * @return Lista de obiecte ParticipantDto.
//     */
//    @GetMapping("")
//    public List<ParticipantDto> getAllParticipants() {
//        return participantService.getAllParticipants();
//    }
//
//    /**
//     * Endpoint pentru adăugarea unui participant.
//     *
//     * @param addParticipantRequest Obiectul de tip AddParticipantRequest care conține informațiile necesare pentru adăugarea unui participant.
//     * @return Obiectul ParticipantDto reprezentând participantul adăugat.
//     */
//    @PostMapping("/addParticipant")
//    public ParticipantDto addParticipant(@RequestBody AddParticipantRequest addParticipantRequest){
//        return participantService.addParticipant(addParticipantRequest);}
//}
