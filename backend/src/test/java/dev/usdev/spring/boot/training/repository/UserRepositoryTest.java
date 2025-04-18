package dev.usdev.spring.boot.training.repository;

import dev.usdev.spring.boot.training.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void truncateTableUserNative() {
        userRepository.truncateTableUserNative();
    }

    @Test
    public void saveUser() {
        User user = new User().builder()
                .userName("jdoe")
                .firstName("John")
                .lastName("Doe")
                .email("jdoe@example.com")
                .build();
        userRepository.save(user);

        assert userRepository.findByUserName(user.getUserName()) != null;
    }


}