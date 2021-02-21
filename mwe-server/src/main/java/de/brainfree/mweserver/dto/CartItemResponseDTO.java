package de.brainfree.mweserver.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
public class CartItemResponseDTO {

    @ApiModelProperty(value = "id of the item", example = "1")
    Long id;

    @ApiModelProperty(value = "id of the product", example = "1")
    Long productId;

    @ApiModelProperty(value = "name of the product", example = "Produkt")
    String productName;

    @ApiModelProperty(value = "text of the product", example = "Text zum Produkt")
    String productText;

    @ApiModelProperty(value = "price of the product", example = "99.22")
    BigDecimal productPrice;

    @ApiModelProperty(value = "quantity of the product in the item", example = "2")
    int quantity;

}
