package com.quizapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionRequest {
    private String text;
    private String type;
    private Integer points;

    private List<ChoiceRequest> choices;
}
