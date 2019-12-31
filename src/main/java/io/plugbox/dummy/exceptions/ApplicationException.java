package io.plugbox.dummy.exceptions;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public ApplicationException(String message) {
        super(message);
    }

}
