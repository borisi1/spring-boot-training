package dev.usdev.spring.boot.training.controller;

import dev.usdev.spring.boot.training.entity.User;
import dev.usdev.spring.boot.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String userName) {
        List<User> users = new ArrayList<>();

        if (userName != null) users.add(userRepository.findByUserName(userName));
        else users = userRepository.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);

    }
}
