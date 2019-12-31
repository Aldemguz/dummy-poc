package io.plugbox.dummy.utils;

import org.springframework.http.HttpStatus;

import io.plugbox.dummy.exceptions.DummyException;
import io.plugbox.dummy.model.Book;

public class Utilities {

	private Utilities() {}

	public static void existsBook(Book book) {
		if(book==null) {
			throw new DummyException(HttpStatus.NOT_FOUND,"Book not found");
		}
	}
}
