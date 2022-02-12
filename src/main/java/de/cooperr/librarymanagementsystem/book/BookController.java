package de.cooperr.librarymanagementsystem.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/book")
public class BookController {

    private final BookRepository repository;

    @PostMapping
    public @ResponseBody
    Book addNewBook(@RequestBody Book book) {
        return repository.save(book);
    }

    @DeleteMapping(path = "/{bookId}")
    public @ResponseBody
    Book deleteBook(@PathVariable UUID bookId) {
        Book book = repository.findById(bookId).orElseThrow(() -> new IllegalStateException("Invalid Book ID"));
        repository.deleteById(bookId);
        return book;
    }

    @GetMapping(path = "/{bookId}")
    public @ResponseBody
    Book getBook(@PathVariable UUID bookId) {
        return repository.findById(bookId).orElse(null);
    }

    @GetMapping
    public @ResponseBody
    Iterable<Book> getAllBooks() {
        return repository.findAll();
    }
}
