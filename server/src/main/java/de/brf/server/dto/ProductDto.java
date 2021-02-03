package de.brf.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Data
@AllArgsConstructor
@Builder
public class ProductDto {

    String name;
    BigDecimal price;
    String category;
    private int amount;
    private String description;

    public ProductDto() {
    }
}
