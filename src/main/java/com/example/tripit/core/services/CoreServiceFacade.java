package com.example.tripit.core.services;

import com.example.tripit.core.persistance.models.Category;
import com.example.tripit.core.persistance.models.User;

import java.util.List;

public interface CoreServiceFacade {

    void updateCategories();

    List<Category> getAllCategories();

    List<Category> getPreferencesById(Long id);

    void checkIfCategoryExists(String category);

    User getUserById(Long userId);
}
