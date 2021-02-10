package de.brf.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private String name;
    private BigDecimal price;
    private String category;
    private String description;

}
