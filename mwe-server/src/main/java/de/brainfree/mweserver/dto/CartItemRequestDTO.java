package de.brainfree.mweserver.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CartItemRequestDTO {

    @ApiModelProperty(value = "id of the product", example = "1", required = true)
    Long productId;

    @ApiModelProperty(value = "quantity of the product to add in a item", example = "1", required = true)
    int quantity;

    // hier reichen dann eigentlich die IDs, weil wir arbeiten ja mit Stammdaten
    // das heißt das Produkt selbst gibt es ja schon und muss nicht mehr vom Frontend kommen
    // ich muss ja nur wissen welche IDs man sich in den Cart gelegt hat

}
