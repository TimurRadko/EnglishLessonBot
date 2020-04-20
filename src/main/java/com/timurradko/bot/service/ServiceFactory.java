package com.timurradko.bot.service;

import com.timurradko.bot.service.answer.AnswerService;
import com.timurradko.bot.service.answer.AnswerServiceImpl;
import com.timurradko.bot.service.course.CourseService;
import com.timurradko.bot.service.course.CourseServiceImpl;
import com.timurradko.bot.service.question.QuestionService;
import com.timurradko.bot.service.question.QuestionServiceImpl;
import com.timurradko.bot.service.teacher.TeacherService;
import com.timurradko.bot.service.teacher.TeacherServiceImpl;
import com.timurradko.bot.service.user.UserService;
import com.timurradko.bot.service.user.UserServiceImpl;

public class ServiceFactory {
    private static UserService userService = new UserServiceImpl();
    private static CourseService courseService = new CourseServiceImpl();
    private static QuestionService questionService = new QuestionServiceImpl();
    private static AnswerService answerService = new AnswerServiceImpl();
    private static TeacherService teacherService = new TeacherServiceImpl();

    public static UserService getUserService() {
        return userService;
    }

    public static CourseService getCourseService() {
        return courseService;
    }

    public static QuestionService getQuestionService() {
        return questionService;
    }

    public static AnswerService getAnswerService() {
        return answerService;
    }

    public static TeacherService getTeacherService() {
        return teacherService;
    }
}
