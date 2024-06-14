package com.example.tripit.core.services;

import com.example.tripit.auth.dtos.LoginDto;
import com.example.tripit.auth.dtos.RegisterDto;
import com.example.tripit.core.mappers.factory.UserFactory;
import com.example.tripit.core.persistance.models.Category;
import com.example.tripit.core.persistance.repositories.UserRepository;
import com.example.tripit.core.persistance.models.User;
import com.example.tripit.exceptions.CredentialsAlreadyExistException;
import com.example.tripit.exceptions.UserNotFoundException;
import com.example.tripit.places.dtos.entities.utils.CategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserFactory userFactory;

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Long> saveUser(RegisterDto registerDto) {
        checkRegisterDetails(registerDto);

        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new CredentialsAlreadyExistException("Username is already exist!");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new CredentialsAlreadyExistException("Email is already exist!");
        }

        Long uid = userRepository.save(generateUser(registerDto)).getId();
        return new ResponseEntity<>(uid, HttpStatus.OK);
    }

    private User generateUser(RegisterDto registerDto) {
        return userFactory.generateUser(registerDto);
    }

    @Override
    public Long getUserId(LoginDto loginDto) {
        return userRepository.findByUsername(loginDto.getUsername())
                .map(User::getId)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    @Override
    public List<Category> getPreferencesById(Long id) {
        return userRepository.findById(id)
                .map(User::getPreferences)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    @Override
    public void updatePreferences(Long id, Set<CategoryId> categories) {
        userRepository.findById(id).map(user -> {
            user.setPreferences(userFactory.getCategoriesFromPreferences(categories));
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    void checkRegisterDetails(RegisterDto registerDto) {
        if(registerDto.getEmail() == null) {
            throw new IllegalArgumentException("Email is empty!");
        } else if(registerDto.getUsername() == null) {
            throw new IllegalArgumentException("Username is empty!");
        } else if(registerDto.getPassword() == null) {
            throw new IllegalArgumentException("Password is empty!");
        }
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }
}
