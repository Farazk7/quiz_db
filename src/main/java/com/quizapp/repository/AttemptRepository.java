package com.quizapp.repository;

import com.quizapp.entity.Answer;
import com.quizapp.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository extends JpaRepository<Attempt,Long> {
}
