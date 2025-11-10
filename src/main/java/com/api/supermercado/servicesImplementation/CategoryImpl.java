package com.api.supermercado.servicesImplementation;

import com.api.supermercado.dtos.CategoryFullResponseDto;
import com.api.supermercado.dtos.CategoryRequestDto;
import com.api.supermercado.entities.Category;
import com.api.supermercado.exceptions.CategoryException;
import com.api.supermercado.exceptions.CategoryExceptions;
import com.api.supermercado.exceptions.ProductException;
import com.api.supermercado.exceptions.ProductExceptions;
import com.api.supermercado.repositories.CategoryRepository;
import com.api.supermercado.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryFullResponseDto> findAllCategoriesActives(Integer lastId, Integer size) {
        if (lastId == null) lastId = 0;
        if (size == null || size <= 0) size = 10;

        return categoryRepository.findActiveCategories(lastId, size);
    }

    @Override
    public List<CategoryFullResponseDto> findAllInactiveCategories(Integer lastId, Integer size) {
        if (lastId == null) lastId = 0;
        if (size == null || size <= 0) size = 10;

        return categoryRepository.findAllInactiveCategories(lastId, size);
    }

    @Override
    public void createCategory(CategoryRequestDto dto) {
        try {
            if (dto == null)
                throw new CategoryException(CategoryExceptions.INVALID_CATEGORY_DATA);

            if (categoryRepository.existsCategoryBynameCategory(dto.nameCategory())) {
                throw new CategoryException(CategoryExceptions.DUPLICATE_CATEGORY);
            }

            Category category = new Category();
            category.setNameCategory(dto.nameCategory());
            category.setDescriptionCategory(dto.descriptionCategory());
            category.setIs_active(true);

            categoryRepository.save(category);

        } catch (CategoryException e) {
            throw e;
        } catch (Exception e) {
            throw new CategoryException(CategoryExceptions.DATABASE_ERROR, e.getMessage());
        }
    }



    @Override
    public Optional<Category> UpdateCategory(String nameCategory, CategoryRequestDto dto) {
        try {
            if (nameCategory == null || nameCategory.isBlank()) {
                throw new CategoryException(CategoryExceptions.INVALID_CATEGORY_DATA);
            }

            Category category = categoryRepository.findByNameCategory(nameCategory)
                    .orElseThrow(() -> new CategoryException(CategoryExceptions.CATEGORY_NOT_FOUND));

            if (!category.getNameCategory().equalsIgnoreCase(dto.nameCategory())) {
                boolean duplicateExists = categoryRepository.existsCategoryBynameCategory(dto.nameCategory());
                if (duplicateExists) {
                    throw new CategoryException(CategoryExceptions.DUPLICATE_CATEGORY);
                }
            }


            category.setNameCategory(dto.nameCategory());
            category.setDescriptionCategory(dto.descriptionCategory());
            category.setIs_active(dto.isActive());


            Category updatedCategory = categoryRepository.save(category);

            return Optional.of(updatedCategory);

        } catch (CategoryException e) {
            throw e;
        } catch (Exception e) {
            throw new CategoryException(CategoryExceptions.DATABASE_ERROR, e.getMessage());
        }
    }

    @Override
    public void deleteCategory(String nameCategory) {
        try {
            if (nameCategory == null || nameCategory.isBlank()) {
                throw new CategoryException(CategoryExceptions.INVALID_CATEGORY_DATA);
            }

            Category category = categoryRepository.findByNameCategory(nameCategory)
                    .orElseThrow(() -> new CategoryException(CategoryExceptions.CATEGORY_NOT_FOUND));

            category.setIs_active(false);

            categoryRepository.save(category);

        } catch (CategoryException e) {
            throw e;
        } catch (Exception e) {
            throw new CategoryException(CategoryExceptions.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Optional<Category> getCategoryByName(String nameCategory) {
        if (nameCategory == null || nameCategory.isBlank())
            throw new ProductException(ProductExceptions.INVALID_PRODUCT_DATA);

        return categoryRepository.findByNameCategory(nameCategory)
                .stream()
                .findFirst();
    }
}
