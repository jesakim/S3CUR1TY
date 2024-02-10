package com.example.springsecurity.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record RegisterDto(
        @NotNull(message = "The first name cannot be null")
        @NotBlank(message = "The first name cannot be blank")
        String firstName,

        @NotNull(message = "The last name cannot be null")
        @NotBlank(message = "The last name cannot be blank")
        String lastName,

        @NotNull(message = "The email address cannot be null")
        @NotBlank(message = "The email address cannot be blank")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "The password cannot be null")
        @NotBlank(message = "The password cannot be blank")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password
) {
}
