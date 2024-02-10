package com.example.springsecurity.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users = new HashSet<>();

    public Authority(int id,String name) {
        this.id = (long)id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }



//    INSERT INTO authority (name)
//    VALUES ('CAN_EDIT'), ('CAN_ADD'), ('CAN_DELETE'),('CAN_VIEW');
}
