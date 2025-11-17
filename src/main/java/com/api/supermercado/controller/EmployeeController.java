package com.api.supermercado.controller;

import com.api.supermercado.dtos.ApiResponse;
import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.dtos.EmployeeRegisterDto;
import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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



    @PutMapping("/updateEmployee")
    public ResponseEntity<?> updateEmployee(
            @Valid
            @RequestParam String identificationNumber,
            @RequestBody EmployeeRegisterDto employeeRegisterDto) {

        return employeeService.updateEmployee(identificationNumber,employeeRegisterDto)
                .map(updateEmployee-> ResponseEntity.ok(Map.of(
                        "message", "Employee successfully updated",
                        "Employee", updateEmployee
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/logicalErase")
    public ResponseEntity<?> logicalEraseCustomer(@Valid @RequestParam String identificationNumber) {
        employeeService.deleteEmployee(identificationNumber);
        return ResponseEntity.ok(Map.of(
                "message", "Employee successfully deleted"
        ));
    }



    @GetMapping("/searchByIdentificationNumber")
    public ResponseEntity<?> getEmployee(
            @Valid
            @RequestParam String identificationNumber) {

        return employeeService.findEmployeeByIdentificationNumber(identificationNumber)
                .map(employee ->
                        ResponseEntity.ok(
                                new ApiResponse<>(
                                        "Employee  obtained successfully.",
                                        1,
                                        employee
                                )
                        )
                )
                .orElseGet(() ->
                        ResponseEntity.ok().body(
                                new ApiResponse<>(
                                        "No Employee found with the given identification number.",
                                        0,
                                        null
                                )
                        )
                );
    }

}
