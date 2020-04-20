package com.timurradko.bot.dao.teachers;

import com.timurradko.bot.dao.config.ConnectionManager;
import com.timurradko.bot.dao.tool.EntityDaoUtil;
import com.timurradko.bot.shared.entity.Teacher;

import java.sql.*;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    private static final String SHOW_ALL_TEACHERS = "SELECT * FROM teachers";
    private static final String CHOSE_TEACHER_FROM_ID = "SELECT * FROM teachers WHERE teacher_id = ?;";

    @Override
    public Teacher getTeacher(Integer teacherId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(CHOSE_TEACHER_FROM_ID);
            statement.setInt(1, teacherId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Teacher teacher = EntityDaoUtil.initTeacher(resultSet);
                return teacher;
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
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SHOW_ALL_TEACHERS);
            teachers = EntityDaoUtil.initTeachers(result);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement, connection);
        }
        return teachers;
    }
}
