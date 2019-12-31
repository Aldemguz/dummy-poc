package io.plugbox.dummy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.plugbox.dummy.model.Book;
import io.plugbox.dummy.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book storeBook(Book book) {

		return bookRepository.save(book);
	}

	public Book getBook(long id) {

		return bookRepository.findById(id);
	}

	public Iterable<Book> getBooks() {

		return bookRepository.findAll();
	}

	public void removeBook(Book book){
		bookRepository.delete(book);
	}
}
