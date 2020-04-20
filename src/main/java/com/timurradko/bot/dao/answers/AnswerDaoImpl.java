package com.timurradko.bot.dao.answers;

import com.timurradko.bot.dao.config.ConnectionManager;
import com.timurradko.bot.dao.tool.EntityDaoUtil;
import com.timurradko.bot.shared.entity.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {
    private static final String GET_ANSWER = "SELECT * FROM answers an " +
            "JOIN questions_has_answers qha on an.answer_id = qha.answer_id " +
            "JOIN questions q on qha.question_id = q.question_id WHERE q.question_id = ?;";
//    private static final String GET_CORRECTNESS_ANSWERS = "SELECT qha.question_id, an.correctness " +
//            "FROM answers an JOIN questions_has_answers qha on an.answer_id = qha.answer_id " +
//            "JOIN questions q on qha.question_id = q.question_id WHERE q.question_id = ?;";

    @Override
    public List<Answer> getAllAnswers(Integer questionId) {
        List<Answer> answers;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(GET_ANSWER);
            statement.setLong(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            answers = EntityDaoUtil.initAnswers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement, connection);
        }
        return answers;
    }


}
