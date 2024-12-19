package com.quiz.Assignment.service;
import com.quiz.Assignment.model.Question;
import com.quiz.Assignment.model.QuizSession;
import com.quiz.Assignment.repository.QuestionRepository;
import com.quiz.Assignment.repository.QuizSessionRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Data
@RequiredArgsConstructor
public class QuizService {
    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    private final QuizSessionRepository quizSessionRepository;

    //to start the quiz
    public QuizSession startQuiz(Long userId) {
        QuizSession session = new QuizSession();
        session.setUserId(userId);
        session.setStartTime(LocalDateTime.now());
        session.setQuestionResults(new HashMap<>());
        return quizSessionRepository.save(session);
    }

    //to get randomQuestion
    public Question getRandomQuestion(Long sessionId) {
        validateSession(sessionId);
        return questionRepository.findRandomQuestion();
    }

    public boolean submitAnswer(Long sessionId, Long questionId, String selectedOption) {
        validateSession(sessionId);
        Question question = questionRepository.findById(questionId).orElseThrow();
        boolean correct = question.getCorrectAnswer().equals(selectedOption);

        QuizSession session = quizSessionRepository.findById(sessionId).orElseThrow();
        session.getQuestionResults().put(questionId, correct);
        quizSessionRepository.save(session);
        return correct;
    }

    //get the stats of questions
    public Map<String, Integer> getStats(Long sessionId) {
        QuizSession session = quizSessionRepository.findById(sessionId).orElseThrow();
        int correctAnswers = (int) session.getQuestionResults().values().stream().filter(v -> v).count();
        int total = session.getQuestionResults().size();
        return Map.of("totalAnswered", total, "correctAnswers", correctAnswers, "incorrectAnswers", total - correctAnswers);
    }

    private void validateSession(Long sessionId) {
        quizSessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found!"));
    }

}
