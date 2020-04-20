package com.timurradko.bot.service.answer;

import com.timurradko.bot.shared.entity.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> getAllAnswers(Integer questionId);
}
