package com.example.tripit.core.mappers.factory;

import com.example.tripit.auth.dtos.RegisterDto;
import com.example.tripit.core.persistance.models.Category;
import com.example.tripit.core.persistance.models.Role;
import com.example.tripit.core.persistance.models.User;
import com.example.tripit.core.persistance.repositories.CategoryRepository;
import com.example.tripit.core.persistance.repositories.RoleRepository;
import com.example.tripit.places.dtos.entities.utils.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFactoryImpl implements UserFactory{

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public User generateUser(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role roles = roleRepository.findByName("USER").orElse(
                new Role(2,"USER")
        );
        user.setRoles(Collections.singleton(roles));
        List<Category> preferences = getCategoriesFromPreferences(registerDto.getPreferences());
        user.setPreferences(preferences);
        return user;
    }

    @Override
    public List<Category> getCategoriesFromPreferences(Set<CategoryId> preferences) {
        return categoryRepository.findAllCategoriesById(preferences.stream().map(CategoryId::getId).toList());
    }
}
