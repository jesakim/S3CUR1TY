package com.example.springsecurity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TokenDto(
        @NotNull(message = "The token cannot be null")
        @NotBlank(message = "The token cannot be blank")
        String token
) {
}
