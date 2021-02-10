package de.brf.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
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
    private Calendar createAt;
    private Calendar updateAt;

}
