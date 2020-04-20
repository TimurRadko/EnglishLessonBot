package com.timurradko.bot.dao.question;

import com.timurradko.bot.dao.config.ConnectionManager;
import com.timurradko.bot.dao.tool.EntityDaoUtil;
import com.timurradko.bot.shared.entity.Question;

import java.sql.*;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private static final String QUESTION_COUNT = "SELECT * FROM questions";
    private static final String GET_QUESTION = "SELECT * FROM questions WHERE question_id = ?;";

    @Override
    public List<Question> showAllTestQuestions() {
        List<Question> questions;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(QUESTION_COUNT);
            questions = EntityDaoUtil.initQuestions(result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement, connection);
        }
        return questions;
    }

    @Override
    public Question getTestQuestion(Integer questionId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(GET_QUESTION);
            statement.setLong(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Question question = EntityDaoUtil.initQuestion(resultSet);
                return question;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement, connection);
        }
        return null;
    }
}
