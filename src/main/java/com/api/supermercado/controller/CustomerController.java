package com.api.supermercado.controller;

import com.api.supermercado.dtos.ApiResponse;
import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.dtos.PersonPageFullResponseDto;
import com.api.supermercado.dtos.ProductRequestDto;
import com.api.supermercado.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/AllAvailableCustomers")
    public ResponseEntity<?> findAllCustomers(
            @Valid
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<PersonPageFullResponseDto> listActiveCustomers = customerService.findAllActiveCustomers(lastId, size);


        if (listActiveCustomers.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            "The list of active Customers is empty.",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "The list of Customers obtained correctly",
                        listActiveCustomers.size(),
                        listActiveCustomers

                )
        );
    }

    @GetMapping("/AllUnAvailableCustomers")
    public ResponseEntity<?> findAllUnAvailableCustomers(
            @Valid
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<PersonPageFullResponseDto> listUnActiveCustomers = customerService.findAllUnActiveCustomers(lastId, size);


        if (listUnActiveCustomers.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            "The list of inactive customers is empty.",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "The list of inactive customers correctly",
                        listUnActiveCustomers.size(),
                        listUnActiveCustomers

                )
        );
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(
            @Valid
            @RequestParam String identificationNumber,
            @RequestBody CustomerRegisterDto customerRegisterDto) {

        return customerService.updateCustomer(identificationNumber,customerRegisterDto)
                .map(updateCustomer-> ResponseEntity.ok(Map.of(
                        "message", "Customer successfully updated",
                        "Customer", updateCustomer
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/logicalErase")
    public ResponseEntity<?> logicalEraseCustomer(@Valid @RequestParam String identificationNumber) {
        customerService.deleteCustomer(identificationNumber);
        return ResponseEntity.ok(Map.of(
                "message", "Customer successfully deleted"
        ));
    }


    @GetMapping("/searchByIdentificationNumber")
    public ResponseEntity<?> getCustomer(
            @Valid
            @RequestParam String identificationNumber) {

        return customerService.findEmployeeByIdentificationNumber(identificationNumber)
                .map(customer ->
                        ResponseEntity.ok(
                                new ApiResponse<>(
                                        "Customer  obtained successfully.",
                                        1,
                                        customer
                                )
                        )
                )
                .orElseGet(() ->
                        ResponseEntity.ok().body(
                                new ApiResponse<>(
                                        "No Costumer found with the given identification number.",
                                        0,
                                        null
                                )
                        )
                );
    }
}
