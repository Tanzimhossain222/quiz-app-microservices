package com.star.quiz_Service.model;


public class QuizDto {
    String CategoryName;
    Integer numberOfQuestions;
    String title;

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "QuizDto{" +
                "CategoryName='" + CategoryName + '\'' +
                ", numberOfQuestions=" + numberOfQuestions +
                ", title='" + title + '\'' +
                '}';
    }
}
