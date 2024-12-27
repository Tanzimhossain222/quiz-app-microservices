package com.star.quiz_Service.feing;

import com.star.quiz_Service.model.QuestionWrapper;
import com.star.quiz_Service.model.ResponseQuiz;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam String numOfQuestions);

    @PostMapping("/question/getQuestions")
    public  ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> ids);

    @PostMapping("/question/getScore")
    public  ResponseEntity<Integer> getScore(@RequestBody List <ResponseQuiz> response);

}
