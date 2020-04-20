package com.timurradko.bot.shared.entity;

public class Question {
    private Integer questionId;
    private String question;

    public Question(Integer questionId, String question) {
        this.questionId = questionId;
        this.question = question;
    }

    public Question(){}

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
