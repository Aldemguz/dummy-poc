package io.plugbox.dummy.controller;

import static io.plugbox.dummy.constants.Constants.BOOK_ID_KEY;
import static io.plugbox.dummy.constants.Constants.BOOK_REQUEST_WITH_ID_URL;
import static io.plugbox.dummy.constants.Constants.POST_BOOK_REQUEST;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.plugbox.dummy.model.Book;
import io.plugbox.dummy.service.BookService;
import io.plugbox.dummy.utils.BookRequest;
import io.plugbox.dummy.utils.Utilities;

@RestController
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	@PostMapping(path=POST_BOOK_REQUEST,
		 	 consumes = MediaType.APPLICATION_JSON_VALUE,
		 	 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> postBook(@Valid @RequestBody BookRequest request ){

		logger.info("Called resource: POST /api/books");

		Book book = new Book(request.getName(),request.getAuthor());

		bookService.storeBook(book);

		logger.info("Book with id: ({}) has been created", book.getId());

		Map<String, String> response = new HashMap<>();
		response.put(BOOK_ID_KEY, String.valueOf(book.getId()));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(path=BOOK_REQUEST_WITH_ID_URL,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> updateBook(@PathVariable("id") long id,
														 @Valid @RequestBody BookRequest request){

		logger.info("Called resource: PUT /api/books/{}", id);

		Book book = bookService.getBook(id);
		Utilities.existsBook(book);

		book.setName(request.getName());
		book.setAuthor(request.getAuthor());

		bookService.storeBook(book);

		logger.info("updated: Book {} in database", book.getId());

		Map<String, String> response = new HashMap<>();
		response.put("book_name", book.getName());
		response.put("book_author", book.getAuthor());
		response.put(BOOK_ID_KEY, String.valueOf(book.getId()));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(path=BOOK_REQUEST_WITH_ID_URL)
	public ResponseEntity<Object> removeBook(@PathVariable("id") long id){

		logger.info("Called resource: DELETE /api/books/{}", id);

		Book book = bookService.getBook(id);
		Utilities.existsBook(book);

		bookService.removeBook(book);

		logger.info("Removed: Book {} from database", book.getId());

		return new ResponseEntity<>("Calendar Has been removed", HttpStatus.OK);
	}

	@GetMapping(path=BOOK_REQUEST_WITH_ID_URL,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> getBook(@PathVariable("id") long id){

		logger.info("Called resource: GET /api/books/{}", id);

		Book book = bookService.getBook(id);
		Utilities.existsBook(book);

		logger.info("Get: Book {} from database", book.getId());

		Map<String, String> response = new HashMap<>();
		response.put("book_name", book.getName());
		response.put("book_author", book.getAuthor());
		response.put(BOOK_ID_KEY, String.valueOf(book.getId()));


		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
