package com.api.supermercado.initializers;

import com.api.supermercado.entities.Gender;
import com.api.supermercado.entities.IdentificationType;
import com.api.supermercado.enums.GenderEnum;
import com.api.supermercado.enums.IdentificationTypeEnum;
import com.api.supermercado.repositories.GenderRepository;
import com.api.supermercado.repositories.IdentificationTypeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final GenderRepository genderRepository;
    private final IdentificationTypeRepository identificationTypeRepository;

    /**
     * Este método se ejecuta automáticamente al iniciar la aplicación.
     */
    @PostConstruct
    public void initData() {
        initIdentificationTypes();
        initGenders();
        System.out.println("✅ Datos inicializados correctamente");
    }

    /**
     * Inicializa los tipos de identificación definidos en el enum.
     */
    private void initIdentificationTypes() {
        for (IdentificationTypeEnum typeEnum : IdentificationTypeEnum.values()) {
            if (!identificationTypeRepository.existsById(typeEnum.getIdentificationTypeEnum())) {
                IdentificationType type = IdentificationType.builder()
                        .idIdentificationType(typeEnum.getIdentificationTypeEnum())
                        .description(typeEnum.name())
                        .build();
                identificationTypeRepository.save(type);
            }
        }
    }

    /**
     * Inicializa los géneros definidos en el enum.
     */
    private void initGenders() {
        for (GenderEnum genderEnum : GenderEnum.values()) {
            if (!genderRepository.existsById(genderEnum.getId())) {
                Gender gender = Gender.builder()
                        .genderId(genderEnum.getId())
                        .description(genderEnum.name())
                        .build();
                genderRepository.save(gender);
            }
        }
    }
}
