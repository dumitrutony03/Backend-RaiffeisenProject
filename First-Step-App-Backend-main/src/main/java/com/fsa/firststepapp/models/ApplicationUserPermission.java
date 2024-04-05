package com.fsa.firststepapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerație care conține permisiunile de utilizator pentru aplicație.
 */
@Getter
@AllArgsConstructor
public enum ApplicationUserPermission {
    LOCATION_CREATE("location:create"),
    LOCATION_READ("location:read"),
    LOCATION_UPDATE("location:update"),
    LOCATION_DELETE("location:delete"),
    EVENT_CREATE("event:create"),
    EVENT_READ("event:read"),
    EVENT_UPDATE("event:update"),
    EVENT_DELETE("event:delete"),
    UNIVERSITY_CREATE("university:create"),
    UNIVERSITY_READ("university:read"),
    UNIVERSITY_UPDATE("university:update"),
    UNIVERSITY_DELETE("university:delete"),
    FACULTY_CREATE("faculty:create"),
    FACULTY_READ("faculty:read"),
    FACULTY_UPDATE("faculty:update"),
    FACULTY_DELETE("faculty:delete");

    private final String permission;
}
