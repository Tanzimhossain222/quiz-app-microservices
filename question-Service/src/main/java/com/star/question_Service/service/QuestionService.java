package com.star.question_Service.service;


import com.star.question_Service.dao.QuestionDao;
import com.star.question_Service.exception.ResourceNotFoundException;
import com.star.question_Service.model.Question;
import com.star.question_Service.model.QuestionWrapper;
import com.star.question_Service.model.ResponseQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public List<Question> getAllQuestion() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategoryIgnoreCase(category);
    }

    public String addQuestion(Question question) {
        try {
            questionDao.save(question);
            return "Question added successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error saving question", e);
        }
    }

    public Question updateQuestion(int id, Question question) {
        Optional<Question> existingQuestionOpt = questionDao.findById(id);

        if (existingQuestionOpt.isEmpty()) {
            return null;
        }

        Question existingQuestion = existingQuestionOpt.get();

        existingQuestion.setQuestionTitle(question.getQuestionTitle());
        existingQuestion.setOption1(question.getOption1());
        existingQuestion.setOption2(question.getOption2());
        existingQuestion.setOption3(question.getOption3());
        existingQuestion.setOption4(question.getOption4());
        existingQuestion.setCorrectAnswer(question.getCorrectAnswer());
        existingQuestion.setCategory(question.getCategory());
        existingQuestion.setDifficultyLevel(question.getDifficultyLevel());

        return questionDao.save(existingQuestion);
    }


    public Question partialUpdateQuestion(int id, Question partialQuestion) {
        Optional<Question> existingQuestionOpt = questionDao.findById(id);

        if (existingQuestionOpt.isEmpty()) {
            return null;
        }

        Question existingQuestion = existingQuestionOpt.get();

        if (partialQuestion.getQuestionTitle() != null) {
            existingQuestion.setQuestionTitle(partialQuestion.getQuestionTitle());
        }
        if (partialQuestion.getOption1() != null) {
            existingQuestion.setOption1(partialQuestion.getOption1());
        }
        if (partialQuestion.getOption2() != null) {
            existingQuestion.setOption2(partialQuestion.getOption2());
        }
        if (partialQuestion.getOption3() != null) {
            existingQuestion.setOption3(partialQuestion.getOption3());
        }
        if (partialQuestion.getOption4() != null) {
            existingQuestion.setOption4(partialQuestion.getOption4());
        }
        if (partialQuestion.getCorrectAnswer() != null) {
            existingQuestion.setCorrectAnswer(partialQuestion.getCorrectAnswer());
        }
        if (partialQuestion.getCategory() != null) {
            existingQuestion.setCategory(partialQuestion.getCategory());
        }
        if (partialQuestion.getDifficultyLevel() != null) {
            existingQuestion.setDifficultyLevel(partialQuestion.getDifficultyLevel());
        }

        return questionDao.save(existingQuestion);

    }

    public Question getQuestionById(int id) {
        return questionDao.findById(id).orElse(null);
    }

    public String deleteQuestion(int id) {
        Optional<Question> existingQuestionOpt = questionDao.findById(id);

        if (existingQuestionOpt.isEmpty()) {
            return "Question not found";
        }

        questionDao.deleteById(id);
        return "Question deleted successfully";
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int numQ) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        if (questions.isEmpty()) {
            throw new ResourceNotFoundException("No questions found for the given category.");
        }

        return ResponseEntity.ok(questions);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> ids) {
        List<QuestionWrapper> wQuestions = new ArrayList<>();
        List<Question>  questions = new ArrayList<>();

        for ( Integer id : ids){
            Question question = questionDao.findById(id).orElse(null);
            if (question == null) {
                throw new ResourceNotFoundException("Question not found for the given id: " + id);
            }
            questions.add(question);
        }

        for (Question question : questions) {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setId(question.getId());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());

            wQuestions.add(wrapper);
        }
        return ResponseEntity.ok(wQuestions);
    }

    public ResponseEntity<Integer> getScore(List<ResponseQuiz> response) {
        int  score = 0;

        for (ResponseQuiz res: response){
            Question question = questionDao.findById(res.getId()).orElse(null);
            if (question == null) {
                throw new ResourceNotFoundException("Question not found for the given id.");
            }


            if (question.getCorrectAnswer().equals(res.getResponse())){
                score++;
            }
        }

        return ResponseEntity.ok(score);
    }
}