package com.star.quiz_Service.service;


import com.star.quiz_Service.dao.QuizDao;
import com.star.quiz_Service.exception.ResourceNotFoundException;
import com.star.quiz_Service.feing.QuizInterface;
import com.star.quiz_Service.model.QuestionWrapper;
import com.star.quiz_Service.model.Quiz;
import com.star.quiz_Service.model.ResponseQuiz;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuizService {
    @Autowired
  private QuizDao quizDao;

    @Autowired
    private QuizInterface quizInterface;


    public Quiz createQuiz(String category, int noOfQuestions, String title) {
        try{

            List<Integer> questions = quizInterface.getQuestionsForQuiz(category, String.valueOf(noOfQuestions)).getBody();

            if (questions.isEmpty()) {
                throw new ResourceNotFoundException("No questions found for the given category.");
            }

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionIds(questions);

            quizDao.save(quiz);
            return quiz;
        } catch (Exception e) {
            throw new RuntimeException("Error creating quiz", e);
        }

    }

    @Transactional
    public List<QuestionWrapper> getQuizQuestions(int id) {

        Quiz quiz = quizDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quiz with ID " + id + " not found"));

        List<Integer> questionIds = quiz.getQuestionIds();

        List<QuestionWrapper> questionWrappers = quizInterface.getQuestionsFromId(questionIds).getBody();

        return questionWrappers;
    }

    public int calculateResult(int id, List<ResponseQuiz> response) {
        ResponseEntity<Integer> score = quizInterface.getScore(response);

        return score.getBody();

    }
}
