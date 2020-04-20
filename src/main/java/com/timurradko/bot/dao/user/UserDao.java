package com.timurradko.bot.dao.user;

import com.timurradko.bot.shared.entity.User;

import java.util.List;

public interface UserDao {
    User getUser(Long chatId);
    void createUser(User user);
    List<User> getAllUsers();
    void changeUserLevel(Long chatId, String userLevel);
    void chooseTheCourse(Long chatId, Integer courseId);
}
