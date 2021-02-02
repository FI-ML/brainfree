package de.brf.server.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Data
@Builder
public class UserDto {
    private String keycloakId;
    private String firstName;
    private String lastName;
    private String email;

    public UserDto(String keycloakId, String firstName, String lastName, String email) {
        this.keycloakId = keycloakId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
