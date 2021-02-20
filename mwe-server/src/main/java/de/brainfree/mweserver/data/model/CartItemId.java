package de.brainfree.mweserver.data.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartItemId implements Serializable {

    private Long cart;
    private Long product;

}
