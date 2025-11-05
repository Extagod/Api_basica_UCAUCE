package com.api.supermercado.services;

import com.api.supermercado.dtos.CategoryFullResponseDto;
import com.api.supermercado.dtos.CategoryRequestDto;
import com.api.supermercado.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryFullResponseDto> findAllCategoriesActives(Integer lastId, Integer size);

    List<CategoryFullResponseDto> findAllInactiveCategories(Integer lastId, Integer size);

    void createCategory(CategoryRequestDto categoryRequestDto);

    Optional<Category> UpdateCategory(String nameCategory, CategoryRequestDto dto);

    void deleteCategory(String nameCategory);

    Optional<Category> getCategoryByName(String nameCategory);
}