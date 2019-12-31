package io.plugbox.dummy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import io.plugbox.dummy.model.Book;

@Repository
@RestResource(exported = false)
public interface BookRepository extends JpaRepository<Book, Long> {

	Book findById(long id);
}
