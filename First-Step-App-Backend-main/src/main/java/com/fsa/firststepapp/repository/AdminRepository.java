package com.fsa.firststepapp.repository;

import com.fsa.firststepapp.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository pentru manipularea datelor entității Admin.
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {

    /**
     * Găsește un utilizator după adresa de email.
     *
     * @param email Adresa de email a adminului căutat.
     * @param password parola adminului căutat.
     * @return Un utilizator opțional (presupune că utilizatorul există sau nu).
     */
    Optional<Admin> findAdminByEmailAndPassword(String email, Long password);
    boolean findAdminByEmail(String email);
}
