package com.api.supermercado.controller;

import com.api.supermercado.dtos.CategoryRequestDto;
import com.api.supermercado.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        categoryService.addCategory(categoryRequestDto);
        return ResponseEntity.ok(Map.of(
                "message", "Category successfully created"
        ));

    }

    @GetMapping("/availableCategories")
    public ResponseEntity<?> getProducts(
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        return ResponseEntity.ok(categoryService.findAllCategoriesActives(lastId, size));
    }

}
