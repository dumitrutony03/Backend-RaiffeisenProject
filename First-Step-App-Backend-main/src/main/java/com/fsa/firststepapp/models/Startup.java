package com.fsa.firststepapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Clasă de entitate care reprezintă participanții la evenimente în aplicație.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="startups")
public class Startup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="startupId")
    private Long startupId;

    @Column(name = "name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private Long password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.valueOf("STARTUP");
}

