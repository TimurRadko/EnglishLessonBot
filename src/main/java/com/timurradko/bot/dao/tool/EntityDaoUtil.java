package com.timurradko.bot.dao.tool;

import com.timurradko.bot.shared.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntityDaoUtil {
    public static User initUser(ResultSet rs) throws SQLException {
        Long chatId = rs.getLong("user_id");
        String username = rs.getString("name");
        Integer age = rs.getInt("age");
        String status = rs.getString("user_status");
        String userLevel = rs.getString("user_level");
        Integer courseId = rs.getInt("course_id");
        return new User(chatId, username, age, status, userLevel, courseId);
    }

    public static List<User> initUsers(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        rs.beforeFirst();
        while (rs.next()) {
            User user = initUser(rs);
            users.add(user);
        }
        return users;
    }

    public static Course initCourseShort(ResultSet rs) throws SQLException {
        Integer courseId = rs.getInt("course_id");
        String title = rs.getString("title");
        Course course = new Course();
        course.setCourseId(courseId);
        course.setTitle(title);
        return course;
    }

    public static List<Course> initCoursesShort(ResultSet rs) throws SQLException {
        List<Course> courses = new ArrayList<>();
        rs.beforeFirst();
        while (rs.next()) {
            Course course = initCourseShort(rs);
            courses.add(course);
        }
        return courses;
    }

    public static Course initCourses(ResultSet rs) throws SQLException {
        Integer courseId = rs.getInt("course_id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        Course course = new Course();
        course.setCourseId(courseId);
        course.setDescription(description);
        course.setTitle(title);
        return course;
    }

    public static Question initQuestion(ResultSet rs) throws SQLException {
        Integer questionId = rs.getInt("question_id");
        String questionIn = rs.getString("question");
        Question question = new Question();
        question.setQuestionId(questionId);
        question.setQuestion(questionIn);
        return question;
    }

    public static List<Question> initQuestions(ResultSet rs) throws SQLException {
        List<Question> questions = new ArrayList<>();
        rs.beforeFirst();
        while (rs.next()) {
            Question question = initQuestion(rs);
            questions.add(question);
        }
        return questions;
    }

    public static Answer initAnswer(ResultSet rs) throws SQLException {
        Integer answerId = rs.getInt("answer_id");
        String answerIn = rs.getString("answer");
        String correctness = rs.getString("correctness");
        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        answer.setAnswer(answerIn);
        answer.setCorrectness(correctness);
        return answer;
    }

    public static List<Answer> initAnswers(ResultSet rs) throws SQLException {
        List<Answer> answers = new ArrayList<>();
        rs.beforeFirst();
        while (rs.next()) {
            Answer answer = initAnswer(rs);
            answers.add(answer);
        }
        return answers;
    }

    public static Teacher initTeacher(ResultSet rs) throws SQLException {
        Integer teacherId = rs.getInt("teacher_id");
        String name = rs.getString("name");
        String primaryLanguage = rs.getString("primary_language");
        String startLevel = rs.getString("start_level");
        Integer courseId = rs.getInt("course_id");
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherName(name);
        teacher.setPrimaryLanguage(primaryLanguage);
        teacher.setStartLevel(startLevel);
        teacher.setCourseId(courseId);
        return teacher;
    }

    public static List<Teacher> initTeachers(ResultSet rs) throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        rs.beforeFirst();
        while (rs.next()) {
            Teacher teacher = initTeacher(rs);
            teachers.add(teacher);
        }
        return teachers;
    }
}
