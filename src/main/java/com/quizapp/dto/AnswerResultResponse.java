package com.quizapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnswerResultResponse {
    private Long questionId;
    private boolean correct;
    private Integer pointsAwarded;
}
