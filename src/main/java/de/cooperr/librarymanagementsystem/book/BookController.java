package de.cooperr.librarymanagementsystem.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping
public class BookController {

    private final BookRepository repository;

    @PostMapping(path = "/book")
    public @ResponseBody
    Book addBook(@RequestBody Book book) {
        return repository.save(book);
    }

    @DeleteMapping(path = "/book")
    public @ResponseBody
    Book deleteBook(@RequestParam UUID bookId) {
        Book book = repository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid Book ID"));

        repository.deleteById(bookId);
        return book;
    }

    @GetMapping(path = "/book")
    public @ResponseBody
    Book getBook(@RequestParam UUID bookId) {
        return repository.findById(bookId).orElse(null);
    }

    @GetMapping(path = "/books")
    public @ResponseBody
    Iterable<Book> getAllBooks() {
        return repository.findAll();
    }
}
