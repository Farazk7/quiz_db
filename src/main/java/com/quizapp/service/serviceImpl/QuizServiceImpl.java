package com.quizapp.service.serviceImpl;

import com.quizapp.dto.*;
import com.quizapp.entity.Choice;
import com.quizapp.entity.Question;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.enums.QuestionType;
import com.quizapp.exception.BadRequestException;
import com.quizapp.exception.ResourceNotFoundException;
import com.quizapp.repository.QuizRepository;
import com.quizapp.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public QuizResponse createQuiz(QuizRequest request) {

        if (request == null || request.getTitle() == null) {
            throw new BadRequestException("Quiz title is required");
        }

        Quiz quiz = Quiz.builder()
                .title(request.getTitle().trim())
                .description(request.getDescription())
                .build();

        if (request.getQuestions() != null) {
            for (QuestionRequest q : request.getQuestions()) {
                if (q == null || q.getText() == null) {
                    throw new BadRequestException("Question text is required");
                }

                QuestionType type;
                try {
                    type = QuestionType.valueOf(q.getType().toUpperCase(Locale.ROOT));
                } catch (Exception ex) {
                    throw new BadRequestException("Invalid question type: " + q.getType());
                }

                Question question = Question.builder()
                        .quiz(quiz)
                        .text(q.getText().trim())
                        .type(type)
                        .points(q.getPoints() == null ? 1 : q.getPoints())
                        .build();

                if (q.getChoices() != null && !q.getChoices().isEmpty()) {
                    question.setChoices(
                            q.getChoices().stream().map(c ->
                                    Choice.builder()
                                            .text(c.getText())
                                            .correct(c.isCorrect())
                                            .question(question)
                                            .build()
                            ).collect(Collectors.toList())
                    );
                }

                quiz.getQuestions().add(question);
            }
        }

        Quiz savedQuiz = quizRepository.save(quiz);
        return mapToQuizResponse(savedQuiz);
    }

    @Override
    public QuizResponse getQuizForPublic(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        return mapToQuizResponse(quiz);
    }

    private QuizResponse mapToQuizResponse(Quiz quiz) {
        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .questions(
                        quiz.getQuestions().stream().map(q ->
                                QuestionResponse.builder()
                                        .id(q.getId())
                                        .text(q.getText())
                                        .type(q.getType().name())
                                        .points(q.getPoints())
                                        .choices(
                                                q.getChoices() != null ?
                                                        q.getChoices().stream()
                                                                .map(c -> ChoiceResponse.builder()
                                                                        .id(c.getId())
                                                                        .text(c.getText())
                                                                        .build()
                                                                ).collect(Collectors.toList())
                                                        : null
                                        )
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build();
    }
}
