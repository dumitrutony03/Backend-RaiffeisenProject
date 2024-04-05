package com.fsa.firststepapp.repository;


import com.fsa.firststepapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository pentru manipularea datelor entității User.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Găsește un utilizator după adresa de email.
     *
     * @param email Adresa de email a adminului căutat.
     * @param password parola adminului căutat.
     * @return Un utilizator opțional (presupune că utilizatorul există sau nu).
     */
//    Optional<User> findAdminByEmailAndPassword(String email, Long password);
    Optional<User> findUserByEmailAndPassword(String email, String password);
    Optional<User> findUserByEmail(String email);
}

