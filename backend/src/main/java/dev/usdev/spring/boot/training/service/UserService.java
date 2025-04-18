package dev.usdev.spring.boot.training.service;


import dev.usdev.spring.boot.training.entity.User;
import dev.usdev.spring.boot.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void deleteAllUsers() {
        userRepository.truncateTableUserNative();
    }

    public void bootstrapTableUser() {
        userRepository.saveAll(Arrays.asList(
                new User().builder()
                        .userName("jdoe1")
                        .firstName("John1")
                        .lastName("Doe1")
                        .email("<EMAIL1>")
                        .build(),
                new User().builder()
                        .userName("jdoe2")
                        .firstName("John2")
                        .lastName("Doe2")
                        .email("<EMAIL2>")
                        .build(),
                new User().builder()
                        .userName("jdoe3")
                        .firstName("John3")
                        .lastName("Doe3")
                        .email("<EMAIL3>")
                        .build()
        ));
    }
}
