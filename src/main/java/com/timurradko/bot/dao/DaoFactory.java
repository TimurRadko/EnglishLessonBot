package com.timurradko.bot.dao;

import com.timurradko.bot.dao.answers.AnswerDao;
import com.timurradko.bot.dao.answers.AnswerDaoImpl;
import com.timurradko.bot.dao.course.CourseDao;
import com.timurradko.bot.dao.course.CourseDaoImpl;
import com.timurradko.bot.dao.question.QuestionDao;
import com.timurradko.bot.dao.question.QuestionDaoImpl;
import com.timurradko.bot.dao.teachers.TeacherDao;
import com.timurradko.bot.dao.teachers.TeacherDaoImpl;
import com.timurradko.bot.dao.user.UserDao;
import com.timurradko.bot.dao.user.UserDaoImpl;

public class DaoFactory {
    private static UserDao userDao = new UserDaoImpl();
    private static CourseDao courseDao = new CourseDaoImpl();
    private static QuestionDao questionDao = new QuestionDaoImpl();
    private static AnswerDao answerDao = new AnswerDaoImpl();
    private static TeacherDao teacherDao = new TeacherDaoImpl();

    public static UserDao getUserDao() {
        return userDao;
    }

    public static CourseDao getCourseDao() {
        return courseDao;
    }

    public static QuestionDao getQuestionDao() {
        return questionDao;
    }

    public static AnswerDao getAnswerDao() {
        return answerDao;
    }

    public static TeacherDao getTeacherDao() {
        return teacherDao;
    }
}
