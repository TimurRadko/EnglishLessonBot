package com.timurradko.bot.controller.tool;

import com.timurradko.bot.shared.entity.*;

import java.util.Collection;

public class UiEntityUtil {
    private static final String COURSE = "The course: ";
    private static final String DESCRIPTION = "Short course description: ";
    private static final String USER_INFORMATION = "User information:";
    private static final String USER_NAME = "Name - ";
    private static final String USER_STATUS = "Status - ";
    private static final String USER_LEVEL = "Language proficiency - ";
    private static final String DELIMITER1 = ";   ";
    private static final String DELIMITER2 = ":   ";
    private static final String DELIMITER3 = "     ";
    private static final String COUNT = "Question number ";
    private static final String TEACHER_NAME = "Mr. (Mrs.):   ";
    private static final String PRIMARY_LANGUAGE = "Primary language - ";
    private static final String START_LEVEL = "Recommended level of training: ";

    public static String coursesToShortString(Collection<Course> courses) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Course course : courses) {
            builder.append("/")
                    .append(++i)
                    .append(DELIMITER3)
                    .append(course.getTitle())
                    .append("\n");
        }
        return builder.toString();
    }

    public static String coursesToString(Course course) {
        StringBuilder builder = new StringBuilder();
        builder.append(COURSE)
                .append(course.getTitle())
                .append("\n")
                .append("\n")
                .append(DESCRIPTION)
                .append(course.getDescription());
        return builder.toString();
    }

    public static String userToString(Collection<User> users) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (User user : users) {
            builder.append("/")
                    .append(++i)
                    .append(DELIMITER3)
                    .append(USER_INFORMATION)
                    .append("\n")
                    .append(USER_NAME)
                    .append(user.getUsername())
                    .append(DELIMITER1)
                    .append(USER_STATUS)
                    .append(user.getStatus())
                    .append(DELIMITER1)
                    .append(USER_LEVEL)
                    .append(user.getUserLevel())
                    .append("\n")
                    .append("\n");
        }
        return builder.toString();
    }

    public static String userToString(User user) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        builder.append(USER_NAME)
                .append(user.getUsername())
                .append("\n")
                .append("/")
                .append(++i)
                .append(DELIMITER3)
                .append(USER_STATUS)
                .append(user.getStatus())
                .append("\n")
                .append("/")
                .append(++i)
                .append(DELIMITER3)
                .append(USER_LEVEL)
                .append(user.getUserLevel())
                .append("\n")
                .append("\n");
        return builder.toString();
    }

    public static String allTestQuestions(Collection<Question> questions) {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (Question question : questions) {
            builder.append(COUNT)
                    .append(++count)
                    .append(DELIMITER2)
                    .append(question.getQuestion())
                    .append("\n");
        }
        return builder.toString();
    }

    public static String answerForQuestion(Collection<Answer> answers) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Answer answer : answers) {
            builder.append("/")
                    .append(++i)
                    .append(DELIMITER3)
                    .append(answer.getAnswer())
                    .append("\n");
        }
        return builder.toString();
    }

    public static String showAllTeachers(Collection<Teacher> teachers) {
        StringBuilder builder = new StringBuilder();
        for (Teacher teacher : teachers) {
            builder.append(TEACHER_NAME)
                    .append(teacher.getTeacherName())
                    .append(DELIMITER1)
                    .append(PRIMARY_LANGUAGE)
                    .append(teacher.getPrimaryLanguage())
                    .append(DELIMITER1)
                    .append(START_LEVEL)
                    .append(teacher.getStartLevel())
                    .append("\n")
                    .append("\n");
        }
        return builder.toString();
    }

    public static String valueToString(Collection<String> values) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String value : values) {
            builder.append("/")
                    .append(++i)
                    .append(DELIMITER3)
                    .append(value)
                    .append("\n");
        }
        return builder.toString();
    }
}
