package com.timurradko.bot.dao.user;

import com.timurradko.bot.dao.config.ConnectionManager;
import com.timurradko.bot.dao.tool.EntityDaoUtil;
import com.timurradko.bot.shared.entity.User;
import com.timurradko.bot.shared.exception.UserFriendlyException;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String SELECT_USER_BY_CHAT_ID = "SELECT * FROM users WHERE user_id=?;";
    private static final String CREATE_USER = "INSERT INTO users (user_id, name, age, user_status, user_level, course_id) VALUES(?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String CHANGE_USER_LEVEL = "UPDATE users SET user_level = ? WHERE user_id = ?;";
    private static final String CHOOSE_THE_COURSE = "UPDATE users SET course_id = ? WHERE user_id = ?;";
    private static final String CHANGE_USER_STATUS = "UPDATE users SET user_status = ? WHERE user_id = ?;";

    @Override
    public User getUser(Long chatId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(SELECT_USER_BY_CHAT_ID);
            statement.setLong(1, chatId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = EntityDaoUtil.initUser(resultSet);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement, connection);
        }
        return null;
    }

    @Override
    public void createUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(CREATE_USER);
            statement.setLong(1, user.getChatId());
            statement.setString(2, user.getUsername());
            statement.setInt(3, user.getAge());
            statement.setString(4, user.getStatus());
            statement.setString(5, user.getUserLevel());
            statement.setInt(6, user.getCourseId());
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new UserFriendlyException("This name is already taken");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(statement, connection);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SELECT_ALL_USERS);
            users = EntityDaoUtil.initUsers(result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement, connection);
        }
        return users;
    }

    @Override
    public void changeUserLevel(Long chatId, String userLevel) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(CHANGE_USER_LEVEL);
            statement.setString(1, userLevel);
            statement.setLong(2, chatId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement, connection);
        }
    }

    @Override
    public void chooseTheCourse(Long chatId, Integer courseId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(CHOOSE_THE_COURSE);
            statement.setInt(1, courseId);
            statement.setLong(2, chatId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement, connection);
        }
    }

    @Override
    public void changeUserStatus(Long chatId, String userStatus) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(CHANGE_USER_STATUS);
            statement.setString(1, userStatus);
            statement.setLong(2, chatId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement, connection);
        }
    }
}
