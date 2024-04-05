package com.fsa.firststepapp.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clasa care reprezintă obiectul de cerere pentru adăugarea de participanți.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddParticipantRequest {
    private String user;
    private Long eventId;
}
