package com.quizapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AttemptRequest {
    private String participantName;
    private Long quizId;
    private List<AnswerRequest> answers;
}
