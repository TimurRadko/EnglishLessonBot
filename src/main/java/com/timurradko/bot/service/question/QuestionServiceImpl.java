package com.timurradko.bot.service.question;

import com.timurradko.bot.dao.DaoFactory;
import com.timurradko.bot.dao.question.QuestionDao;
import com.timurradko.bot.shared.entity.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
   private QuestionDao questionDao = DaoFactory.getQuestionDao();

    @Override
    public Question getQuestion(Integer questionId) {
        Question question = questionDao.getTestQuestion(questionId);
        return question;
    }

    @Override
    public List<Question> getAllTestQuestion() {
        List<Question> allTestQuestion = questionDao.showAllTestQuestions();
        return allTestQuestion;
    }
}
