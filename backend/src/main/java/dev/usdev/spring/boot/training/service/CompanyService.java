package dev.usdev.spring.boot.training.service;

import dev.usdev.spring.boot.training.entity.Company;
import dev.usdev.spring.boot.training.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public void bootstrapTableCompany() {
        companyRepository.saveAll(Arrays.asList(
                new Company().builder()
                        .companyName("Company1")
                        .companyPhoneNumber("1234567890")
                        .companyAddress("CompanyAddress1")
                        .companyDescription("CompanyDescription1")
                        .build(),
                new Company().builder()
                        .companyName("Company2")
                        .companyPhoneNumber("1234567890")
                        .companyAddress("CompanyAddress2")
                        .companyDescription("CompanyDescription2")
                        .build(),
                new Company().builder()
                        .companyName("Company3")
                        .companyPhoneNumber("1234567890")
                        .companyAddress("CompanyAddress3")
                        .companyDescription("CompanyDescription4")
                        .build()
        ));
    }

    public void deleteAllCompanies() {
        companyRepository.truncateTableCompanyNative();
    }
}
