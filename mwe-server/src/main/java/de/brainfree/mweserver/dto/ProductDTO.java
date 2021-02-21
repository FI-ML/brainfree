package de.brainfree.mweserver.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
public class ProductDTO {

    @ApiModelProperty(value = "id of the product", example = "1")
    Long id;

    @ApiModelProperty(value = "name of the product", example = "Produkt", required = true)
    String name;

    @ApiModelProperty(value = "text of the product", example = "Text zum Produkt", required = true)
    String text;

    @ApiModelProperty(value = "price of the product", example = "99.22", required = true)
    BigDecimal price;

}
