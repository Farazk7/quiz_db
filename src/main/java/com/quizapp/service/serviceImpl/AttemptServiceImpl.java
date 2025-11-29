package com.quizapp.service.serviceImpl;

import com.quizapp.dto.AnswerRequest;
import com.quizapp.dto.AnswerResultResponse;
import com.quizapp.dto.AttemptRequest;
import com.quizapp.dto.AttemptResponse;
import com.quizapp.entity.*;
import com.quizapp.exception.BadRequestException;
import com.quizapp.exception.ResourceNotFoundException;
import com.quizapp.repository.AttemptRepository;
import com.quizapp.repository.ChoiceRepository;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.repository.QuizRepository;
import com.quizapp.service.AttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttemptServiceImpl implements AttemptService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;
    private final AttemptRepository attemptRepository;

    @Override
    public AttemptResponse submitAttempt(AttemptRequest request) {

        if (request == null || request.getQuizId() == null || request.getAnswers() == null) {
            throw new BadRequestException("Invalid attempt request");
        }

        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        Attempt attempt = Attempt.builder()
                .participantName(request.getParticipantName())
                .quiz(quiz)
                .totalPoints(quiz.getQuestions().stream().mapToInt(Question::getPoints).sum())
                .score(0)
                .build();

        List<AnswerResultResponse> answerResultList = new ArrayList<>();
        int score = 0;

        for (AnswerRequest ansReq : request.getAnswers()) {
            if (ansReq == null || ansReq.getQuestionId() == null) {
                throw new BadRequestException("Each answer must contain questionId");
            }

            Question question = questionRepository.findById(ansReq.getQuestionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Question not found"));

            Answer answer = new Answer();
            answer.setAttempt(attempt);
            answer.setQuestion(question);

            boolean isCorrect = false;
            int pointsAwarded = 0;

            switch (question.getType()) {
                case MCQ:
                case TRUE_FALSE:
                    if (ansReq.getChoiceId() == null) {
                        throw new BadRequestException("ChoiceId is required for MCQ/TRUE_FALSE questions");
                    }

                    Choice selected = choiceRepository.findById(ansReq.getChoiceId())
                            .orElseThrow(() -> new ResourceNotFoundException("Choice not found"));

                    answer.setChoice(selected);

                    if (selected.isCorrect()) {
                        isCorrect = true;
                        pointsAwarded = question.getPoints();
                        score += pointsAwarded;
                    }
                    break;

                case TEXT:
                    String provided = ansReq.getTextAnswer();
                    String expected = question.getTextAnswerHint();

                    answer.setTextAnswer(provided);

                    if (expected != null && provided != null &&
                            provided.trim().equalsIgnoreCase(expected.trim())) {
                        isCorrect = true;
                        pointsAwarded = question.getPoints();
                        score += pointsAwarded;
                    }
                    break;

                default:
                    throw new BadRequestException("Unsupported question type: " + question.getType());
            }

            answer.setIsCorrect(isCorrect);
            answer.setPointsAwarded(pointsAwarded);
            attempt.getAnswers().add(answer);

            answerResultList.add(
                    AnswerResultResponse.builder()
                            .questionId(question.getId())
                            .correct(isCorrect)
                            .pointsAwarded(pointsAwarded)
                            .build()
            );
        }

        attempt.setScore(score);
        Attempt saved = attemptRepository.save(attempt);

        return AttemptResponse.builder()
                .attemptId(saved.getId())
                .score(score)
                .totalPoints(saved.getTotalPoints())
                .answers(answerResultList)
                .build();
    }
}
