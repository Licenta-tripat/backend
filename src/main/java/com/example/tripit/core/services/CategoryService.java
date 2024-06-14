package com.example.tripit.core.services;

import com.example.tripit.core.persistance.models.Category;

import java.util.List;

public interface CategoryService {
    void updateCategories();

    List<Category> getAllCategories();

    void checkIfCategoryExists(String category);
}
