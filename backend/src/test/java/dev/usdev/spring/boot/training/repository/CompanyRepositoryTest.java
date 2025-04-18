package dev.usdev.spring.boot.training.repository;

import dev.usdev.spring.boot.training.entity.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void truncateTableCompanyNative() {
        companyRepository.truncateTableCompanyNative();
    }

    @Test
    public void saveCompany() {

        Company company = new Company().builder()
                .companyName("Company1")
                .companyPhoneNumber("1234567890")
                .build();
        companyRepository.save(company);
    }

}