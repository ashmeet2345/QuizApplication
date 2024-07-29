package com.example.QuizApp.repository;

import com.example.QuizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value ="SELECT * FROM QUESTION Q WHERE Q.CATEGORY=:category ORDER BY RANDOM() LIMIT 1" ,nativeQuery = true)
    List<Question> findRandomlyByCategory(String category);
}
