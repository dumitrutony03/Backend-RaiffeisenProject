package com.fsa.firststepapp.models;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fsa.firststepapp.models.ApplicationUserPermission.*;

/**
 * Enumerație care definește rolurile disponibile pentru utilizatori în aplicație.
 */
public enum ApplicationUserRole {
    USER(Sets.newHashSet(EVENT_READ, LOCATION_READ, UNIVERSITY_READ, FACULTY_READ)),
    ADMIN(Sets.newHashSet(EVENT_READ, EVENT_CREATE, EVENT_UPDATE, EVENT_DELETE,
            LOCATION_CREATE, LOCATION_READ, LOCATION_DELETE, LOCATION_UPDATE,
            UNIVERSITY_CREATE, UNIVERSITY_DELETE, UNIVERSITY_READ, UNIVERSITY_UPDATE,
            FACULTY_CREATE, FACULTY_DELETE, FACULTY_READ, FACULTY_UPDATE
            ));

    @Getter
    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions){
        this.permissions=permissions;
    }

    /**
     * Obține o listă de obiecte SimpleGrantedAuthority pentru rolul curent.
     * @return Lista de obiecte SimpleGrantedAuthority.
     */
    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = getPermissions()
                .stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}
