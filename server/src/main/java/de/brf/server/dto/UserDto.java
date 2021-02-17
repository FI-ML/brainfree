package de.brf.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;

}
