package com.quizapp.service.serviceImpl;

import com.quizapp.config.JWTUtils;
import com.quizapp.dto.LoginRequest;
import com.quizapp.dto.LoginResponse;
import com.quizapp.entity.User;
import com.quizapp.exception.UnauthorizedException;
import com.quizapp.repository.UserRepository;
import com.quizapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        // PRINT HASH FROM DB
        System.out.println("DB HASH = " + user.getPassword());
        System.out.println("RAW = " + request.getPassword());
        System.out.println("MATCH = " + passwordEncoder.matches(request.getPassword(), user.getPassword()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        String token = jwtUtils.generateToken(user.getEmail());

        return LoginResponse.builder()
                .token(token)
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
