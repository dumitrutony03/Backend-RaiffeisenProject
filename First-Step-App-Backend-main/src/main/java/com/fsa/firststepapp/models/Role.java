package com.fsa.firststepapp.models;

import jakarta.persistence.OneToMany;

/**
 * Enumerație care reprezintă rolurile disponibile în aplicație.
 */
public enum Role {
    INVESTOR,
    STARTUP,
    ADMIN
}

