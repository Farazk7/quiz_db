package com.quizapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class QuestionResponse {
    private Long id;
    private String text;
    private String type;
    private Integer points;

    private List<ChoiceResponse> choices; 
}
