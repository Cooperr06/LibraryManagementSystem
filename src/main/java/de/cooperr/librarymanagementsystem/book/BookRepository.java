package de.cooperr.librarymanagementsystem.book;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BookRepository extends CrudRepository<Book, UUID> {
}
