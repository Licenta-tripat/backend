package com.example.tripit.auth.services;

import com.example.tripit.auth.dtos.LoginDto;
import com.example.tripit.auth.dtos.RegisterDto;
import com.example.tripit.core.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    public ResponseEntity<Long> registerUser(RegisterDto registerDto) {
        return userService.saveUser(registerDto);
    }

    public ResponseEntity<Long> loginUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(userService.getUserId(loginDto), HttpStatus.OK);
    }
}
