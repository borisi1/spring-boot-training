package dev.usdev.spring.boot.training.component;

import dev.usdev.spring.boot.training.service.CompanyService;
import dev.usdev.spring.boot.training.service.TypeService;
import dev.usdev.spring.boot.training.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
@Component
@Profile("dev")
public class DatabaseInitializer implements ApplicationRunner {

    @Value("${app.springboottraining.db.bootstrap}")
    private boolean bootstrap;

    @Autowired
    private TypeService typeService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (bootstrap) {
            typeService.deleteAllTypes();
            typeService.bootstrapTableType();
            userService.deleteAllUsers();
            userService.bootstrapTableUser();
            companyService.deleteAllCompanies();
            companyService.bootstrapTableCompany();
        }
    }
}
