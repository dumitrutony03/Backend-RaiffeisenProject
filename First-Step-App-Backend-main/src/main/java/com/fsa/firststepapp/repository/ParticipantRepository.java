package com.fsa.firststepapp.repository;

import com.fsa.firststepapp.models.Investor;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository pentru manipularea datelor entității Participant.
 */
public interface ParticipantRepository extends CrudRepository<Investor, Long> {
}
