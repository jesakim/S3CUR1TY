package com.example.springsecurity.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class Response<T> {
    private String error;
    private String message;
    private T result;
    private Map<String, String> errorValidation;
}
