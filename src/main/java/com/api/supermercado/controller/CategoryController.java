package com.api.supermercado.controller;

import com.api.supermercado.dtos.ApiResponse;
import com.api.supermercado.dtos.CategoryFullResponseDto;
import com.api.supermercado.dtos.CategoryRequestDto;
import com.api.supermercado.dtos.ProductRequestDto;
import com.api.supermercado.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        categoryService.createCategory(categoryRequestDto);
        return ResponseEntity.ok(Map.of(
                "message", "Category successfully created"
        ));

    }

    @GetMapping("/unavailableCategories")
    public ResponseEntity<?> getInactiveCategories(
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<CategoryFullResponseDto> data = categoryService.findAllInactiveCategories(lastId, size);

        if (data.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            "There are no registered inactive categories",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "There are no registered active categories",
                        data.size(),
                        data

                )
        );
    }


    @GetMapping("/availableCategories")
    public ResponseEntity<?> getActiveCategories(
            @RequestParam(defaultValue = "0") Integer lastId,
            @RequestParam(defaultValue = "10") Integer size) {

        List<CategoryFullResponseDto> data = categoryService.findAllCategoriesActives(lastId, size);

        if (data.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(
                            "There are no records of inactive categories",
                            0,
                            List.of()
                    )
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Active category records successfully uploaded",
                        data.size(),
                        data
                )
        );
    }


    @PutMapping("/updateCategory")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryRequestDto categoryRequestDto, @RequestParam String nameCategory){
        String nameCategoryUpper = nameCategory.toLowerCase().strip();
        return categoryService.UpdateCategory(nameCategory, categoryRequestDto)
                .map(updatedProduct -> ResponseEntity.ok(Map.of(
                        "message", "Product successfully updated",
                        "product", updatedProduct
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/logicalErase")
    public ResponseEntity<?> logicalEraseCategory(@RequestParam String nameCategory) {
        categoryService.deleteCategory(nameCategory);
        return ResponseEntity.ok(Map.of(
                "message", "Category successfully deleted"
        ));
    }

    @GetMapping("/searchCategoryName")
    public ResponseEntity<?> getProduct(
            @RequestParam String nameCategory) {

        return categoryService.getCategoryByName(nameCategory)
                .map(category ->
                        ResponseEntity.ok(
                                new ApiResponse<>(
                                        "Category obtained successfully.",
                                        1,
                                        category
                                )
                        )
                )
                .orElseGet(() ->
                        ResponseEntity.ok().body(
                                new ApiResponse<>(
                                        "No category found with the name category.",
                                        0,
                                        null
                                )
                        )
                );
    }

}
