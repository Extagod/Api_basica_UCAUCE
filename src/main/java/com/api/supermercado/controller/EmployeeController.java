package com.api.supermercado.controller;

import com.api.supermercado.dtos.ApiResponse;
import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/AllAvailableEmployees")
    public ResponseEntity<?> findAllActiveEmployees(
            @Valid
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<PersonPageFullResponseDto> listActiveEmployees = employeeService.findAllActiveEmployees(lastId, size);


        if (listActiveEmployees.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            "The list of active Employees is empty.",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "The list of Employees obtained correctly",
                        listActiveEmployees.size(),
                        listActiveEmployees

                )
        );
    }

    @GetMapping("/AllUnAvailableEmployees")
    public ResponseEntity<?> findAllUnActiveEmployees(
            @Valid
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<PersonPageFullResponseDto> listUnActiveEmployees = employeeService.findAllUnActiveEmployees(lastId, size);


        if (listUnActiveEmployees.isEmpty()) {
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
                        listUnActiveEmployees.size(),
                        listUnActiveEmployees

                )
        );
    }













//    @GetMapping("/searchByIdentificationNumber")
//    ResponseEntity<?> findPersonByIdentificationNumber(@Valid @RequestParam String identification_number) {
//        return personService.findPersonByIdentificationNumber(identification_number)
//                .map(person ->
//                        ResponseEntity.ok(
//                                new ApiResponse<>(
//                                        "Person obtained successfully.",
//                                        1,
//                                        person
//                                )
//                        )
//                )
//                .orElseGet(() ->
//                        ResponseEntity.ok().body(
//                                new ApiResponse<>(
//                                        "No Person found with the given identification number.",
//                                        0,
//                                        null
//                                )
//                        )
//                );
//    }
//


}
