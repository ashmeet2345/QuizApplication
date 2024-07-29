package com.example.QuizApp.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWrapper {

    private int id;

    private String option_1;

    private String option_2;

    private String option_3;

    private String option_4;

    private String question_title;


}
