package com.star.quiz_Service.controller;




import com.star.quiz_Service.exception.ResourceNotFoundException;
import com.star.quiz_Service.model.*;
import com.star.quiz_Service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> getQuiz(@RequestBody QuizDto quizDto) {
        try{
            Quiz result = quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumberOfQuestions(), quizDto.getTitle());
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No questions found for the given category.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the quiz.");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
        List<QuestionWrapper> result = quizService.getQuizQuestions(id);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable int id, @RequestBody List<ResponseQuiz> response) {
        try {
            Integer score = quizService.calculateResult(id, response);
            int totalQuestions = response.size();

            QuizSubmissionResponse submissionResponse = new QuizSubmissionResponse();
            submissionResponse.setScore(score);
            submissionResponse.setTotalQuestions(totalQuestions);
            submissionResponse.setMessage("Quiz submitted successfully. Thank you for participating!");

            return ResponseEntity.ok(submissionResponse);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while submitting the quiz.");
        }
    }




}
