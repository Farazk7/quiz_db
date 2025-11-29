package com.quizapp.service;

import com.quizapp.dto.LoginRequest;
import com.quizapp.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
