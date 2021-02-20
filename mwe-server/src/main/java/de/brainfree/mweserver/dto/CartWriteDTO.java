package de.brainfree.mweserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
@AllArgsConstructor
public class CartWriteDTO {

    Long id;
    Set<CartItemWriteDTO> items;

}
