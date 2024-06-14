package com.example.tripit.core.mappers.factory;

import com.example.tripit.auth.dtos.RegisterDto;
import com.example.tripit.core.persistance.models.Category;
import com.example.tripit.core.persistance.models.User;
import com.example.tripit.places.dtos.entities.utils.CategoryId;

import java.util.List;
import java.util.Set;

public interface UserFactory {

    public User generateUser(RegisterDto registerDto);

    List<Category> getCategoriesFromPreferences(Set<CategoryId> preferences);
}
