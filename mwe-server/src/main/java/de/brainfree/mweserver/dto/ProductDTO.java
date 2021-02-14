package de.brainfree.mweserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
public class ProductDTO {

    Long id;
    String name;
    String text;
    BigDecimal price;

}
