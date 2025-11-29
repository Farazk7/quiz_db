package com.quizapp.service;

import com.quizapp.entity.User;

public interface UserService {
    User findByEmail(String email);
}
