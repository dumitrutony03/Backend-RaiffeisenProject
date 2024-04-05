package com.fsa.firststepapp.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Obiectul de transfer de date (DTO) pentru Startupuri.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartupDto {
    private Long startupId;
    private String name;
    private String email;
    private Long password;
    private String role = "STARTUP";
}