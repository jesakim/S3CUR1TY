package com.example.springsecurity.dto.response;

import com.example.springsecurity.model.Authority;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String role,
        Set<Authority> authorities
        ) {
}
