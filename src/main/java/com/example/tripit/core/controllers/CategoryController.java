package com.example.tripit.core.controllers;

import com.example.tripit.core.services.UserService;
import com.example.tripit.places.dtos.entities.utils.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final UserService userService;

    @PutMapping("/{id}")
    public void updateCategories(@PathVariable("id") Long id, @RequestBody Set<CategoryId> preferences) {
        userService.updatePreferences(id, preferences);
    }
}
