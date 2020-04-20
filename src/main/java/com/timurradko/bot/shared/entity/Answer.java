package com.timurradko.bot.shared.entity;

public class Answer {
    private Integer answerId;
    private String answer;
    private String correctness;

    public Answer() {}

    public Answer(Integer answerId, String answer, String correctness) {
        this.answerId = answerId;
        this.answer = answer;
        this.correctness = correctness;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCorrectness() {
        return correctness;
    }

    public void setCorrectness(String correctness) {
        this.correctness = correctness;
    }
}
