package com.timurradko.bot.controller.base;

import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.shared.entity.Answer;
import com.timurradko.bot.shared.entity.Course;
import com.timurradko.bot.shared.entity.Question;
import com.timurradko.bot.shared.entity.User;

import java.util.List;

public class UserSession {
    private User user;
    private Command nextCommand;
    private Integer userLevel;
    private List<Course> allCourses;
    private List<User> allUsers;
    private List<Question> allTestQuestion;
    private List<Answer> allAnswerForQuestion;
    private Question nextQuestion;
    private Long IdForAdmin;
    private String courseTitle;
    private String courseDescription;

    public UserSession(User user) {
        this.user = user;
    }

    public void clearNextCommandIfExists() {
        if (nextCommand != null) {
            nextCommand = null;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Command getNextCommand() {
        return nextCommand;
    }

    public void setNextCommand(Command nextCommand) {
        this.nextCommand = nextCommand;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }

    public void setAllCourses(List<Course> allCourses) {
        this.allCourses = allCourses;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    public List<Question> getAllTestQuestion() {
        return allTestQuestion;
    }

    public void setAllTestQuestion(List<Question> allTestQuestion) {
        this.allTestQuestion = allTestQuestion;
    }

    public List<Answer> getAllAnswerForQuestion() {
        return allAnswerForQuestion;
    }

    public void setAllAnswersForQuestion(List<Answer> allAnswerForQuestion) {
        this.allAnswerForQuestion = allAnswerForQuestion;
    }

    public Question getNextQuestion(int i) {
        List<Question> questions = allTestQuestion;
        nextQuestion = questions.get(i);
        return nextQuestion;
    }

    public void setNextQuestion(Question nextQuestion) {
        this.nextQuestion = nextQuestion;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Long getIdForAdmin() {
        return IdForAdmin;
    }

    public void setIdForAdmin(Long idForAdmin) {
        this.IdForAdmin = idForAdmin;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
}
