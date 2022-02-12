package de.cooperr.librarymanagementsystem.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "book")
public class Book {

    @Id
    private UUID bookId = UUID.randomUUID();
    private UUID userLoanedId;
    private String name;
    private String author;
    private String publisher;
    private String publishedAt;
}
