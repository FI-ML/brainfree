package de.brf.server.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "keycloak_id")
    private String keycloakId;

    public User() {
    }

    public User(String firstName, String lastName, String email, String keycloakId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.keycloakId = keycloakId;
    }
}
