package com.example.tripit.core.services;

import com.example.tripit.auth.dtos.LoginDto;
import com.example.tripit.auth.dtos.RegisterDto;
import com.example.tripit.core.persistance.models.Category;
import com.example.tripit.core.persistance.models.User;
import com.example.tripit.places.dtos.entities.utils.CategoryId;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface UserService {

    ResponseEntity<Long> saveUser(RegisterDto user);

    Long getUserId(LoginDto loginDto);

    List<Category> getPreferencesById(Long id);

    void updatePreferences(Long id, Set<CategoryId> categories);

    User getUserById(Long id);
}
