package de.brf.server.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Builder
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String UUID;

}
