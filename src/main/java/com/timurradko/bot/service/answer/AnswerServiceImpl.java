package com.timurradko.bot.service.answer;

import com.timurradko.bot.dao.DaoFactory;
import com.timurradko.bot.dao.answers.AnswerDao;
import com.timurradko.bot.shared.entity.Answer;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    private AnswerDao answerDao = DaoFactory.getAnswerDao();

    @Override
    public List<Answer> getAllAnswers(Integer questionId) {
        List<Answer> answers = answerDao.getAllAnswers(questionId);
        return answers;
    }
}
