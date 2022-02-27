package de.cooperr.librarymanagementsystem.library;

import de.cooperr.librarymanagementsystem.book.Book;
import de.cooperr.librarymanagementsystem.book.BookRepository;
import de.cooperr.librarymanagementsystem.user.User;
import de.cooperr.librarymanagementsystem.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/library")
public class LibraryController {

    private UserRepository users;
    private BookRepository books;

    @PostMapping(value = "/loan")
    public @ResponseBody
    Book loanBooks(@RequestParam UUID bookId, @RequestParam UUID userId) {
        Book book = books.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid Book ID"));

        if (book.getUserLoanedId() != null) {
            throw new IllegalArgumentException("Book " + book.getBookId() + " is already being loaned!");
        }

        book.setUserLoanedId(userId);
        books.save(book);

        return book;
    }

    @DeleteMapping("/loan")
    public @ResponseBody
    Book removeLoan(@RequestParam UUID bookId) {
        Book book = books.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid Book ID"));

        book.setUserLoanedId(null);
        books.save(book);

        return book;
    }

    @GetMapping(path = "/loan")
    public @ResponseBody
    User getLoaningUser(@RequestParam UUID bookId) {
        return users.findById(books.findById(bookId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid Book ID")).getUserLoanedId())
                .orElse(null);
    }

    @GetMapping(path = "/loans")
    public @ResponseBody
    Map<UUID, UUID> getLoanedBooks() {
        HashMap<UUID, UUID> userLoanedBooks = new HashMap<>();

        books.findAll().forEach(book -> {
            if (book.getUserLoanedId() != null) {
                userLoanedBooks.put(book.getBookId(), users.findById(book.getUserLoanedId()).orElseThrow(() ->
                        new IllegalArgumentException("Invalid User ID")).getUserId());
            }
        });
        return userLoanedBooks;
    }
}
