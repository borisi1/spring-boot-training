package dev.usdev.spring.boot.training.controller;

import dev.usdev.spring.boot.training.entity.User;
import dev.usdev.spring.boot.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // CREATE
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User saved = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // READ all
    @GetMapping
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    // READ by userName
    @GetMapping("/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName) {

        return userRepository.findByUserName(userName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE by userName
    @PutMapping("/{userName}")
    public ResponseEntity<User> updateUser(
            @PathVariable String userName,
            @RequestBody User userUpdate) {

        return userRepository.findByUserName(userName)
                .map(existing -> {
                    existing.setUserName(userUpdate.getUserName());
                    // …copy other fields as needed
                    User saved = userRepository.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE by userName
    @DeleteMapping("/{userName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {

        return userRepository.findByUserName(userName)
                .map(u -> {
                    userRepository.delete(u);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
