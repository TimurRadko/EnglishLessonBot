package com.timurradko.bot.service.question;

import com.timurradko.bot.shared.entity.Question;

import java.util.List;

public interface QuestionService {
    Question getQuestion(Integer questionId);
    List<Question> getAllTestQuestion();
}
