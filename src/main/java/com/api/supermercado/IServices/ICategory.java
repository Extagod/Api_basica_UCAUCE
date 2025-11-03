package com.api.supermercado.IServices;

import com.api.supermercado.dtos.CategoryFullResponseDto;
import com.api.supermercado.dtos.CategoryRequestDto;
import com.api.supermercado.entities.Category;
import com.api.supermercado.entities.Product;
import com.api.supermercado.exceptions.CategoryException;
import com.api.supermercado.exceptions.CategoryExceptions;
import com.api.supermercado.exceptions.ProductException;
import com.api.supermercado.exceptions.ProductExceptions;
import com.api.supermercado.mappers.ProductRequestMapper;
import com.api.supermercado.repositories.CategoryRepository;
import com.api.supermercado.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICategory implements CategoryService {



    @Autowired
    private final CategoryRepository categoryRepository;

    public ICategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override

    public void addCategory(CategoryRequestDto categoryRequestDto) {
        boolean exists = categoryRepository.existsCategoryBynameCategory(categoryRequestDto.getNameCategory());
        if (exists) {
            throw new CategoryException(CategoryExceptions.DUPLICATE_CATEGORY);
        }
        Category category = new Category();

        category.setNameCategory(categoryRequestDto.getNameCategory());
        category.setDescriptionCategory(category.getDescriptionCategory());
        category.setIs_active(categoryRequestDto.getIsActive());

        categoryRepository.save(category);
    }


    @Override
    public List<CategoryFullResponseDto> findAllCategoriesActives(Integer lastId, Integer size) {
        if (lastId == null) lastId = 0;
        if (size == null || size <= 0) size = 10;

        return categoryRepository.findActiveCategories(lastId, size);
    }

}
