package de.cooperr.librarymanagementsystem.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping
public class UserController {

    private final UserRepository repository;

    @PostMapping(path = "/user")
    public @ResponseBody
    User addNewUser(@RequestBody User user) {
        return repository.save(user);
    }

    @DeleteMapping(path = "/user")
    public @ResponseBody
    User deleteUser(@RequestParam UUID userId) {
        User user = repository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));

        repository.deleteById(userId);
        return user;
    }

    @GetMapping(path = "/user")
    public @ResponseBody
    User getUser(@RequestParam UUID userId) {
        return repository.findById(userId).orElse(null);
    }

    @GetMapping(path = "/users")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return repository.findAll();
    }
}
