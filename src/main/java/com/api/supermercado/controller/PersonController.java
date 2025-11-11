package com.api.supermercado.controller;

import com.api.supermercado.dtos.ApiResponse;
import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {


    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/AllAvailablePersons")
    public ResponseEntity<?> findAllPersons(
            @Valid
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<PersonPageFullResponseDto> listPersons = personService.findAllAvailablePersons(lastId, size);


        if (listPersons.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            "The list of active users is empty.",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "The list of people obtained correctly",
                        listPersons.size(),
                        listPersons

                )
        );
    }
<<<<<<< HEAD

    @GetMapping("/AllUnAvailablePersons")
    public ResponseEntity<?> findAllUnAvailablePersons(
            @Valid
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<PersonPageFullResponseDto> listPersons = personService.findAllUnAvailablePersons(lastId, size);


        if (listPersons.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            "The list of inactive users is empty.",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "The list of people inactive correctly",
                        listPersons.size(),
                        listPersons

                )
        );
    }

    @GetMapping("/searchByIdentificationNumber")
    ResponseEntity<?> findPersonByIdentificationNumber(@Valid @RequestParam String identification_number) {
        return personService.findPersonByIdentificationNumber(identification_number)
                .map(person ->
                        ResponseEntity.ok(
                                new ApiResponse<>(
                                        "Person obtained successfully.",
                                        1,
                                        person
                                )
                        )
                )
                .orElseGet(() ->
                        ResponseEntity.ok().body(
                                new ApiResponse<>(
                                        "No Person found with the given identification number.",
                                        0,
                                        null
                                )
                        )
                );
    }
=======
>>>>>>> 783acced48623f32da4d9415b0c948b80e801dbb
}
