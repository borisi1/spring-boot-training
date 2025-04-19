package dev.usdev.spring.boot.training.controller;

import dev.usdev.spring.boot.training.dto.HomeDto;
import dev.usdev.spring.boot.training.dto.LinkDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {

    @GetMapping("/api/home")
    public HomeDto home() {
        List<LinkDto> links = List.of(
                new LinkDto("User", "/users"),
                new LinkDto("Company", "/companies")
        );
        return new HomeDto(links);
    }
}
