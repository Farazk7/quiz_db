package com.quizapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizRequest {
    private String title;
    private String description;
    private List<QuestionRequest> questions;
}
