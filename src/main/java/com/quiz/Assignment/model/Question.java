package com.quiz.Assignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    @ElementCollection
    @CollectionTable(name = "question_options",joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "options")
    private List<String> options;
    @Column(name = "correct_answer")
    private String correctAnswer;}
