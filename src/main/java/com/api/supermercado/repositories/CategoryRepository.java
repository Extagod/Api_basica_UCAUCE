package com.api.supermercado.repositories;

import com.api.supermercado.dtos.CategoryFullResponseDto;
import com.api.supermercado.dtos.CategoryRequestDto;
import com.api.supermercado.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsCategoryBynameCategory(String nameCategory);


    @Query(value = """

            SELECT 
            c.category_id AS categoryId,
            c.name_category AS nameCategory,
            c.description_category AS descriptionCategory,
            c.is_active AS is_active
        FROM category c
        WHERE c.is_active = true
          AND c.category_id > :lastCategoryId
        ORDER BY c.category_id
        LIMIT :pageSize
        """,
            nativeQuery = true)
    List<CategoryFullResponseDto> findActiveCategories(
            @Param("lastCategoryId") Integer lastCategoryId,
            @Param("pageSize") Integer pageSize
    );
}

