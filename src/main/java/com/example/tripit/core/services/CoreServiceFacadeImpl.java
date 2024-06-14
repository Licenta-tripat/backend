package com.example.tripit.core.services;

import com.example.tripit.core.persistance.models.Category;
import com.example.tripit.core.persistance.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoreServiceFacadeImpl implements CoreServiceFacade{

    private final UserService userService;

    private final CategoryService categoryService;

    @Override
    public void updateCategories() {
        categoryService.updateCategories();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Override
    public List<Category> getPreferencesById(Long id) {
        return userService.getPreferencesById(id);
    }

    @Override
    public void checkIfCategoryExists(String category) {
        categoryService.checkIfCategoryExists(category);
    }

    @Override
    public User getUserById(Long userId) {
        return userService.getUserById(userId);
    }
}
