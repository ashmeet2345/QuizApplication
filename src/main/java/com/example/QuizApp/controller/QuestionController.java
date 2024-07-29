package com.example.QuizApp.controller;

import com.example.QuizApp.model.Question;
import com.example.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService  questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question> > getAllQuestions(){
        return questionService.getAllQuestions();
        //ResponseEntity is used to return data as well as status code to the client
    }

    @GetMapping("allQuestions/{category}")
    public ResponseEntity<List<Question> > getQuestionsByCategory(@PathVariable("category") String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

}
