package com.example.springsecurity.service;


import com.example.springsecurity.dto.request.LoginDto;
import com.example.springsecurity.dto.request.RegisterDto;
import com.example.springsecurity.dto.response.TokenDto;
import com.example.springsecurity.enums.Role;
import com.example.springsecurity.enums.TokenType;
import com.example.springsecurity.model.Authority;
import com.example.springsecurity.model.User;
import com.example.springsecurity.model.Token;
import com.example.springsecurity.repository.AuthorityRepository;
import com.example.springsecurity.repository.TokenRepository;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Response<TokenDto> register(RegisterDto registerDto) {
        Authority authority = authorityRepository.findById(4L).orElseThrow(() -> new RuntimeException("Authority not found"));

        Set<Authority> authorities = Set.of(authority);
        userRepository.findByEmail(registerDto.email()).ifPresent(user -> {
            throw new IllegalArgumentException("User with email " + registerDto.email() + " already exists");
        });
        var user = User.builder()
                .firstName(registerDto.firstName())
                .lastName(registerDto.lastName())
                .email(registerDto.email())
                .password(passwordEncoder.encode(registerDto.password()))
                .role(Role.USER)
                .authorities(authorities)
                .build();
        userRepository.save(user);


        return saveAndGetTokenResponse(user);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private Response<TokenDto> saveAndGetTokenResponse(User user) {
        revokeAllUserTokens(user);
        String jwt = jwtService.generateToken(user);

        var token = Token.builder()
                .token(jwt)
                .user(user)
                .tokenType(TokenType.BEARER)
                .isRevoked(false)
                .isExpired(false)
                .build();
        tokenRepository.save(token);

        var tokenDto = TokenDto.builder()
                .token(jwt)
                .tokenType(TokenType.BEARER)
                .build();

        return Response.<TokenDto>
                builder().
                result(tokenDto).
                build();
    }

    public Response<TokenDto> login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.password()
                )
        );
        User user = userRepository.findByEmail(loginDto.email()).orElseThrow(() -> new RuntimeException("User with email " + loginDto.email() + " not found"));
        return saveAndGetTokenResponse(user);
    }

    public Response<TokenDto> refreshToken(com.example.springsecurity.dto.request.TokenDto tokenDto) {
        Token userToken = tokenRepository.findByToken(tokenDto.token()).orElseThrow(() -> new RuntimeException("Token not found"));
        if (userToken.isRevoked() || userToken.isExpired())
            throw new RuntimeException("Token is revoked or expired");
        User user = userToken.getUser();
        return saveAndGetTokenResponse(user);
    }
}
