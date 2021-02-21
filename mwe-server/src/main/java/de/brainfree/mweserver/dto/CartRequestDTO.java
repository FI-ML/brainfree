package de.brainfree.mweserver.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CartRequestDTO {

    @ApiModelProperty(value = "name of the cart", example = "Warenkorb", required = true)
    String name;

    @ApiModelProperty(value = "owner of the cart", example = "thomas.maier", required = true)
    String username;

}
