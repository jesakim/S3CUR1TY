package com.example.springsecurity.model;


import com.example.springsecurity.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean isExpired;

    private boolean isRevoked;
}
