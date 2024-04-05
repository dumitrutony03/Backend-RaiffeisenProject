package com.fsa.firststepapp.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clasa care reprezintă obiectul de răspuns pentru ștergere.
 * --------------->>>>>>>>> Caz in care un STARTUP vrea sa anuleze meet-ul pe TEAMS cu un INVESTITOR
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteResponse {
    private String message;
}
