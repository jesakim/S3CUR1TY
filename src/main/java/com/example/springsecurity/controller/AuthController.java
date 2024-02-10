package com.example.springsecurity.controller;


import com.example.springsecurity.dto.request.LoginDto;
import com.example.springsecurity.dto.request.RegisterDto;
import com.example.springsecurity.dto.response.TokenDto;
import com.example.springsecurity.service.AuthService;
import com.example.springsecurity.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Response<TokenDto>> register(@Valid @RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(authService.register(registerDto));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<TokenDto>> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Response<TokenDto>> refresh(@Valid @RequestBody com.example.springsecurity.dto.request.TokenDto tokenDto) {
        return ResponseEntity.ok(authService.refreshToken(tokenDto));
    }
}
