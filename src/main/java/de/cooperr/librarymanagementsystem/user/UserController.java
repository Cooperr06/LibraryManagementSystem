package de.cooperr.librarymanagementsystem.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final UserRepository repository;

    @PostMapping
    public @ResponseBody
    User addNewUser(@RequestBody User user) {
        return repository.save(user);
    }

    @DeleteMapping(path = "/{userId}")
    public @ResponseBody
    User deleteUser(@PathVariable UUID userId) {
        User user = repository.findById(userId).orElseThrow(() -> new IllegalStateException("Invalid User ID"));
        repository.deleteById(userId);
        return user;
    }

    @GetMapping(path = "/{userId}")
    public @ResponseBody
    User getUser(@PathVariable UUID userId) {
        return repository.findById(userId).orElse(null);
    }

    @GetMapping
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return repository.findAll();
    }
}
