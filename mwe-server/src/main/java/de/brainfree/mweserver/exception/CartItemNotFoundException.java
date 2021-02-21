package de.brainfree.mweserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartItemNotFoundException extends RuntimeException {

    public CartItemNotFoundException(Long id) {
        super(String.format("Cart item not found for id %s", id));
    }

}
