package io.plugbox.dummy.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class DummyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus status;

    public DummyException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
