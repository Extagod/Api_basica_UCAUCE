package com.api.supermercado.services;

import com.api.supermercado.dtos.CategoryFullResponseDto;
import com.api.supermercado.dtos.CategoryRequestDto;

import java.util.List;

public interface CategoryService {
    void addCategory(CategoryRequestDto categoryRequestDto);

    List<CategoryFullResponseDto> findAllCategoriesActives(Integer lastId, Integer size);
}
