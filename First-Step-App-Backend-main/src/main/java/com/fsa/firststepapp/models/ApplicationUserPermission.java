package com.fsa.firststepapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerație care conține permisiunile de utilizator pentru aplicație.
 */
@Getter
@AllArgsConstructor
public enum ApplicationUserPermission {
//    LOCATION_CREATE("location:create"),
//    LOCATION_READ("location:read"),
//    LOCATION_UPDATE("location:update"),
//    LOCATION_DELETE("location:delete");
//

    MEETUP_CREATE("meetup:create");

    private final String permission;
}
