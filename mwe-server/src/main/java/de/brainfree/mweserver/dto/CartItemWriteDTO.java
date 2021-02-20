package de.brainfree.mweserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CartItemWriteDTO {

    Long id;
    Long productId;
    int quantity;

    // hier reichen dann eigentlich die IDs, weil wir arbeiten ja mit Stammdaten
    // das heißt das Produkt selbst gibt es ja schon und muss nicht mehr vom Frontend kommen
    // ich muss ja nur wissen welche IDs man sich in den Cart gelegt hat

}
