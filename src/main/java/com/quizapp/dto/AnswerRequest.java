package com.quizapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequest {
    private Long questionId;
    private Long choiceId;
    private String textAnswer;
}
