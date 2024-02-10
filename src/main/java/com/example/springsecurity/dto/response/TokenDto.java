package com.example.springsecurity.dto.response;

import com.example.springsecurity.enums.TokenType;
import lombok.Builder;

@Builder
public record TokenDto(
        String token,
        TokenType tokenType
) {
}
