package com.timurradko.bot.dao.question;

import com.timurradko.bot.shared.entity.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> showAllTestQuestions();
    Question getTestQuestion(Integer questionId);
}
