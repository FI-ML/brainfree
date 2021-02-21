package de.brainfree.mweserver.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class CartResponseDTO {

    @ApiModelProperty(value = "name of the cart", example = "Warenkorb")
    String name;

    @ApiModelProperty(value = "owner of the cart", example = "thomas.maier")
    String username;

    @ApiModelProperty(value = "items of the cart")
    List<CartItemResponseDTO> items;

    @ApiModelProperty(value = "sum of all items in the cart", example = "100")
    BigDecimal priceSum;

}
