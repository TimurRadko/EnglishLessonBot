package com.timurradko.bot.dao.answers;

import com.timurradko.bot.shared.entity.Answer;
import com.timurradko.bot.shared.entity.Question;

import java.util.List;

public interface AnswerDao {
    List<Answer> getAllAnswers(Integer questionId);
}
