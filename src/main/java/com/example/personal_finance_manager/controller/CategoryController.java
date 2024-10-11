package com.example.personal_finance_manager.controller;

import com.example.personal_finance_manager.entity.Category;
import com.example.personal_finance_manager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @QueryMapping
    public Category category(@Argument Long id) {
        return categoryService.getCategoryById(id);
    }

    @QueryMapping
    public List<Category> allCategories() {
        return categoryService.getAllCategories();
    }

    @MutationMapping
    public Category createCategory(@Argument String name) {
        Category category = new Category();
        category.setName(name);
        return categoryService.createCategory(category);
    }

    // Implement other mutation methods
}