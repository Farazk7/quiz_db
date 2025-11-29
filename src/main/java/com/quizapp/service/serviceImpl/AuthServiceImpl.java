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

        if (request == null || request.getEmail() == null || request.getPassword() == null) {
            throw new UnauthorizedException("Invalid email or password");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

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
