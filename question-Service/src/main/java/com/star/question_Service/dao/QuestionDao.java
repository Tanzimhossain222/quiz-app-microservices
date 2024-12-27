package com.star.question_Service.dao;

import com.star.question_Service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategoryIgnoreCase(String category);

    @Query(value = "SELECT q.id FROM question q WHERE q.category ILIKE :category ORDER BY RANDOM() LIMIT :noOfQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int noOfQuestions);
}