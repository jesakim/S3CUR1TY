package com.example.springsecurity.exception;

import com.example.springsecurity.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response<String>> handelIllegalArgumentException(Exception ex){
        var response = Response.<String>builder()
                .error(ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<String>> handelMethodArgumentNotValidException(MethodArgumentNotValidException ev){
        Map<String, String> FieldsValidation = new HashMap<>();
        ev.getBindingResult().getFieldErrors().forEach(
                e -> FieldsValidation.put(e.getField(), e.getDefaultMessage())
        );

        var response = Response.<String>builder()
                .errorValidation(FieldsValidation)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response<String>> handelRuntimeException(Exception ex){
        var response = Response.<String>builder()
                .error(ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
