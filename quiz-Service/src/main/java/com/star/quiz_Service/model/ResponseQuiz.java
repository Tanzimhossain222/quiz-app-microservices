package com.star.quiz_Service.model;


public class ResponseQuiz {
    private Integer id;
    private String response;

    public ResponseQuiz(Integer id, String response) {
        this.id = id;
        this.response = response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ResponseQuiz{" +
                "id=" + id +
                ", response='" + response + '\'' +
                '}';
    }

}
