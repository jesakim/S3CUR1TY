package com.example.springsecurity;

import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityApplication {
    public static void main(String[] args) {

        SpringApplication.run(SpringSecurityApplication.class, args);

    }

}
