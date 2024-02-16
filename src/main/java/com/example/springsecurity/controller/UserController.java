package com.example.springsecurity.controller;


import com.example.springsecurity.dto.response.UserDto;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<Response<UserDto>> me() {
        return ResponseEntity.ok(userService.me());
    }

    @GetMapping("/meWithAuthorities")
    public ResponseEntity<Response<UserDto>> meWithAuthorities() {
        return ResponseEntity.ok(userService.me());
    }

}
