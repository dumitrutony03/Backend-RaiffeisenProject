//package com.fsa.firststepapp.models;
//
//import com.google.common.collect.Sets;
//import lombok.Getter;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import static com.fsa.firststepapp.models.ApplicationUserPermission.*;
//
///**
// * Enumerație care definește rolurile disponibile pentru utilizatori în aplicație.
// */
//@Component
//@Getter
//public enum ApplicationUserRole {
//    INVESTOR(Sets.newHashSet(MEETUP_CREATE)),
//    STARTUP(Sets.newHashSet()),
//    ADMIN(Sets.newHashSet(
//            MEETUP_CREATE
//    ));
//
//    private final Set<ApplicationUserPermission> permissions;
//
//    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
//        this.permissions = permissions;
//    }
//
//    /**
//     * Obține o listă de obiecte SimpleGrantedAuthority pentru rolul curent.
//     *
//     * @return Lista de obiecte SimpleGrantedAuthority.
//     */
//    public List<SimpleGrantedAuthority> getAuthorities() {
//        var authorities = getPermissions()
//                .stream()
//                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
//                .collect(Collectors.toList());
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        return authorities;
//    }
//}
