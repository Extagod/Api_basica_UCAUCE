package com.api.supermercado.controller;

import com.api.supermercado.repositories.PersonRepository;
import com.api.supermercado.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class PersonController {


    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity <?> findAllPersons(
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size){
        return ResponseEntity.ok(personService.findAllAvalavailablePersons(lastId, size));


    }
}
