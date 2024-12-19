package com.quiz.Assignment.repository;

import com.quiz.Assignment.model.QuizSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizSessionRepository extends JpaRepository<QuizSession, Long> {
    Optional<QuizSession> findByUserIdAndStartTimeIsNotNull(Long userId);
}
