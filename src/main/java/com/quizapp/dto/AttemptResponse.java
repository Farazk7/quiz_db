package com.quizapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AttemptResponse {
    private Long attemptId;
    private Integer score;
    private Integer totalPoints;
    private List<AnswerResultResponse> answers;
}
