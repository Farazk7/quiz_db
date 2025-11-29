package com.quizapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChoiceResponse {
    private Long id;
    private String text;
}
