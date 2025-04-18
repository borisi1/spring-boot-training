package dev.usdev.spring.boot.training.service;

import dev.usdev.spring.boot.training.entity.Type;
import dev.usdev.spring.boot.training.entity.TypeEnum;
import dev.usdev.spring.boot.training.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public void deleteAllTypes() {
        typeRepository.truncateTableTypeNative();
    }

    public void bootstrapTableType() {
        typeRepository.saveAll(Arrays.asList(
                new Type(null, TypeEnum.DEBIT),
                new Type(null, TypeEnum.CREDIT),
                new Type(null, TypeEnum.UTILITY)
        ));
    }
}
