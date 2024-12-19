package com.quiz.Assignment.controller;
import com.quiz.Assignment.model.Question;
import com.quiz.Assignment.model.QuizSession;
import com.quiz.Assignment.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    //to start the quiz
    @PostMapping("/start")
    public ResponseEntity<QuizSession> startQuiz(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        return ResponseEntity.ok(quizService.startQuiz(userId));
    }

    //to get the question
    @GetMapping("/question/{sessionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long sessionId) {
        return ResponseEntity.ok(quizService.getRandomQuestion(sessionId));
    }

    //submit the question
    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitAnswer(@RequestBody Map<String, Object> request) {
        Long sessionId = ((Number) request.get("sessionId")).longValue();
        Long questionId = ((Number) request.get("questionId")).longValue();
        String selectedOption = (String) request.get("selectedOption");
        boolean correct = quizService.submitAnswer(sessionId, questionId, selectedOption);

        return ResponseEntity.ok(Map.of("correct", correct, "message", correct ? "Correct answer!" : "Incorrect answer!"));
    }

    //get the stats of question
    @GetMapping("/stats/{sessionId}")
    public ResponseEntity<Map<String, Integer>> getStats(@PathVariable Long sessionId) {
        return ResponseEntity.ok(quizService.getStats(sessionId));
    }
}
