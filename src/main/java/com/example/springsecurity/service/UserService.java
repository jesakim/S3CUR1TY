package com.example.springsecurity.service;

import com.example.springsecurity.dto.response.UserDto;
import com.example.springsecurity.model.Authority;
import com.example.springsecurity.model.User;
import com.example.springsecurity.utils.Response;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    public Response<UserDto> me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            var user = (User) authentication.getPrincipal();
            System.out.println(user);
            var userDto = UserDto.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .role(user.getRole().name())
                    .authorities((Set<Authority>) user.getAuthorities())
                    .build();
            return Response.<UserDto>builder()
                    .result(userDto)
                    .build();
        }
        return Response.<UserDto>builder()
                .error("User not found")
                .build();
    }
}
