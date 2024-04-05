package com.fsa.firststepapp.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Obiectul de transfer de date (DTO) pentru Investitori.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestorDto {
    private Long investorId;
    private String name;
    private String email;
    private Long password;
    private String role = "INVESTOR";
}