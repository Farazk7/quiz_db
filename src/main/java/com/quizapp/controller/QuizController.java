package com.quizapp.controller;

import com.quizapp.dto.AttemptRequest;
import com.quizapp.dto.AttemptResponse;
import com.quizapp.dto.QuizResponse;
import com.quizapp.service.QuizService;
import com.quizapp.service.AttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final AttemptService attemptService;

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizResponse> getQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizService.getQuizForPublic(quizId));

    }

    @PostMapping("/attempt")
    public ResponseEntity<AttemptResponse> submitAttempt(@RequestBody AttemptRequest request) {
        return ResponseEntity.ok(attemptService.submitAttempt(request));
    }
}
