package com.example.QuizApp.service;

import com.example.QuizApp.model.Question;
import com.example.QuizApp.model.QuestionWrapper;
import com.example.QuizApp.model.Quiz;
import com.example.QuizApp.model.Response;
import com.example.QuizApp.repository.QuestionRepo;
import com.example.QuizApp.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;
    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, String title){
        Quiz quiz = new Quiz();
        quiz.setTitle(title);

        List<Question> questions=questionRepo.findByCategory(category);
        quiz.setQuestion(questions);
        quizRepo.save(quiz);

        return new ResponseEntity("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz= quizRepo.findById(id);
        List<QuestionWrapper> questions=new ArrayList<>();
        for(Question q:quiz.get().getQuestion()){
            questions.add(new QuestionWrapper(q.getId(),
                    q.getOption_1(),
                    q.getOption_2(),
                    q.getOption_3(),
                    q.getOption_4(),
                    q.getQuestion_title()));
        }

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Optional<Quiz> quiz=quizRepo.findById(id);
        List<Question> questions=quiz.get().getQuestion();

        int right=0;
        int i=0;
        for(Response response:responses){
            if(response.getResponse().equals(questions.get(i++).getRight_answer()))
                right++;
        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
