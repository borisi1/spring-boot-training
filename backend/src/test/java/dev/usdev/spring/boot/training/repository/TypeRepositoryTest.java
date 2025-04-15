package dev.usdev.spring.boot.training.repository;

import dev.usdev.spring.boot.training.entity.Type;
import dev.usdev.spring.boot.training.entity.TypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
// @DataJpaTest
class TypeRepositoryTest {

    @Autowired
    private TypeRepository typeRepository;

    @Test
    public void saveType() {

        Type type = new Type().builder()
                .type(TypeEnum.DEBIT)
                .build();
        typeRepository.save(type);
    }
}