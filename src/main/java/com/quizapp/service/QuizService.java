package com.quizapp.service;

import com.quizapp.dto.QuizRequest;
import com.quizapp.dto.QuizResponse;

public interface QuizService {
    QuizResponse createQuiz(QuizRequest request);
    QuizResponse getQuizForPublic(Long quizId);
}
