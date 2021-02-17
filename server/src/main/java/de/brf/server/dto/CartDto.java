package de.brf.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {

    private String name;
    private Set<Long> productIds;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
