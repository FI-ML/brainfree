package de.brainfree.mweserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NotYetImplementedException extends RuntimeException {

    public NotYetImplementedException(String function) {
        super(String.format("Not yet implemented: %s", function));
    }

}
