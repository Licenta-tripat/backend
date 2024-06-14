package com.example.tripit.auth.controllers;

import com.example.tripit.auth.services.AuthService;
import com.example.tripit.auth.dtos.LoginDto;
import com.example.tripit.auth.dtos.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Long> authenticateUser(@RequestBody LoginDto loginDto) {
        return authService.loginUser(loginDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto signUpDto){
        return authService.registerUser(signUpDto);
    }

}
