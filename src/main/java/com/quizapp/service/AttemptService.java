package com.quizapp.service;

import com.quizapp.dto.AttemptRequest;
import com.quizapp.dto.AttemptResponse;

public interface AttemptService {
    AttemptResponse submitAttempt(AttemptRequest request);
}
