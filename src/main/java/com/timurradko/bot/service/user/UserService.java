package com.timurradko.bot.service.user;

import com.timurradko.bot.shared.entity.User;

import java.util.List;

public interface UserService {
    User getUser(Long chatId);
    void createUser(User user);
    List<User> getAllUsers(Long currentChatId);
    void changeUserLevel(Long chatId, String userLevel);
    void chooseTheCourse(Long chatId, Integer courseId);
    void changeUserStatus(Long chatId, String userStatus);
}
