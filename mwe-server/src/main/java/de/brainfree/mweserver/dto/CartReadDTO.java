package de.brainfree.mweserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class CartReadDTO {

    List<CartItemReadDTO> items;
    BigDecimal priceSum; // das hier muss man ja nicht in der DB speichern, das kann man ja dynamisch berechnen und dann ans Frontend schicken

}
