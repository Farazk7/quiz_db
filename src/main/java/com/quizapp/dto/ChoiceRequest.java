package com.quizapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChoiceRequest {
    private String text;
    private boolean correct;
}
