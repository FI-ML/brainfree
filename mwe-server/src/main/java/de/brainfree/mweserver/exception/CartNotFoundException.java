package de.brainfree.mweserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(Long id) {
        super(String.format("Cart not found for id %s", id));
    }

}
