package de.brainfree.mweserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
public class CartItemReadDTO {

    Long id;
    Long productId;
    String productName;
    String productText;
    BigDecimal productPrice;
    int quantity;

}
