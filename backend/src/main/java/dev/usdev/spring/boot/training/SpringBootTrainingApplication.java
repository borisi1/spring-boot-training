package dev.usdev.spring.boot.training;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Slf4j
public class SpringBootTrainingApplication {

    public static void main(String[] args) {
        log.info("Starting Spring Boot Training Application, running on {}", System.getProperty("spring.profiles.active"));
        log.info("Running on thread {}", Thread.currentThread().getName());
        SpringApplication.run(SpringBootTrainingApplication.class, args);
    }

}
