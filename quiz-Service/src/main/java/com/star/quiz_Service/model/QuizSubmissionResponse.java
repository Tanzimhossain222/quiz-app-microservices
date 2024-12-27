package com.star.quiz_Service.model;

public class QuizSubmissionResponse {
    private int score;
    private int totalQuestions;
    private String message;


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "QuizSubmissionResponse{" +
                "score=" + score +
                ", totalQuestions=" + totalQuestions +
                ", message='" + message + '\'' +
                '}';
    }
}
