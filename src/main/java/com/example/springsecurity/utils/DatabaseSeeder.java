package com.example.springsecurity.utils;

import com.example.springsecurity.model.Authority;
import com.example.springsecurity.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        seedAuthorities();
    }

    //    INSERT INTO authority (name)
//    VALUES ('CAN_EDIT'), ('CAN_ADD'), ('CAN_DELETE'),('CAN_VIEW');

    public void seedAuthorities() {
        if (authorityRepository.count() == 0) {
        authorityRepository.saveAll(List.of(
                new Authority(1, "CAN_EDIT"),
                new Authority(2, "CAN_ADD"),
                new Authority(3, "CAN_DELETE"),
                new Authority(4, "CAN_VIEW")
        ));
        }
    }
}
