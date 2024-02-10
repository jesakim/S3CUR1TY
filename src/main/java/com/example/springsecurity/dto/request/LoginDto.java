package com.example.springsecurity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @NotNull(message = "The email cannot be null")
        @NotBlank(message = "The email cannot be blank")
        String email,
        @NotNull(message = "The password cannot be null")
        @NotBlank(message = "The password cannot be blank")
        String password
) { }
