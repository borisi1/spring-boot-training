package dev.usdev.spring.boot.training.controller;

import dev.usdev.spring.boot.training.entity.Company;
import dev.usdev.spring.boot.training.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping("/api/company")
    public ResponseEntity<List<Company>> getCompanies(@RequestParam(required = false) String companyName) {

        List<Company> companies = new ArrayList<>();

        if (companyName != null) companies.add(companyRepository.getCompaniesByCompanyName(companyName));
        else companies = companyRepository.findAll();
        
        return ResponseEntity.ok(companies);

    }
}
