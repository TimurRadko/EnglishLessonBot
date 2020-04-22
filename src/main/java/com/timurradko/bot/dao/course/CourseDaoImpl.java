package com.timurradko.bot.dao.course;

import com.timurradko.bot.dao.config.ConnectionManager;
import com.timurradko.bot.dao.tool.EntityDaoUtil;
import com.timurradko.bot.shared.entity.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao{
    private static final String SELECT_ALL_COURSES = "SELECT course_id, title FROM courses;";
    private static final String SELECT_FULL_INFORMATION = "SELECT * FROM courses WHERE course_id = ?;";
    private static final String SHOW_MY_COURSE = "SELECT * FROM courses cs " +
            "JOIN users us ON us.course_id = cs.course_id WHERE us.course_id = ?;";
    private static final String ADD_NEW_COURSE = "INSERT INTO courses(title, description) VALUES (?, ?);";
    private static final String DELETE_COURSE = "DELETE FROM courses WHERE course_id = ?;";

    @Override
    public List<Course> getAllCoursesShort() {
        List<Course> courses;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_COURSES);
            courses = EntityDaoUtil.initCoursesShort(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement,connection);
        }
        return courses;
    }

    @Override
    public Course getMyCourseShort(int id) {
        Course myCourse;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(SHOW_MY_COURSE);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            myCourse = EntityDaoUtil.initCourses(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement,connection);
        }
        return myCourse;
    }

    @Override
    public Course getFullCourse(int id) {
        Course course;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(SELECT_FULL_INFORMATION);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            course = EntityDaoUtil.initCourses(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement,connection);
        }
        return course;
    }

    @Override
    public void addNewCourse(String title, String description) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(ADD_NEW_COURSE);
            statement.setString(1,title);
            statement.setString(2,description);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement,connection);
        }
    }

    @Override
    public void deleteCourse(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(DELETE_COURSE);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement,connection);
        }
    }
}
