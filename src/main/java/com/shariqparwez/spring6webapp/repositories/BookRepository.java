package com.shariqparwez.spring6webapp.repositories;

import com.shariqparwez.spring6webapp.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
