package com.timurradko.bot.service.user;

import com.timurradko.bot.dao.DaoFactory;
import com.timurradko.bot.dao.user.UserDao;
import com.timurradko.bot.shared.entity.User;
import com.timurradko.bot.shared.entity.security.Feature;
import com.timurradko.bot.shared.exception.PermissionDeniedException;
import com.timurradko.bot.shared.tool.SecurityUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = DaoFactory.getUserDao();

    @Override
    public User getUser(Long chatId) {
        User user = userDao.getUser(chatId);
        return user;
    }

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public List<User> getAllUsers(Long currentChatId) {
        User user = getUser(currentChatId);
        if (!SecurityUtil.hasFeature(user, Feature.SHOW_USERS_FOR_ADMIN)) {
            throw new PermissionDeniedException();
        }
        List<User> allUsers = userDao.getAllUsers();
        allUsers.sort((o1, o2) -> {
            Integer compare1 = o1.getRole().getCompare();
            Integer compare2 = o2.getRole().getCompare();
            return compare1.compareTo(compare2);
        });
        return allUsers;
    }

    @Override
    public void changeUserLevel(Long chatId, String userLevel) {
        userDao.changeUserLevel(chatId, userLevel);
    }

    @Override
    public void chooseTheCourse(Long chatId, Integer courseId) {
        userDao.chooseTheCourse(chatId, courseId);
    }

    @Override
    public void changeUserStatus(Long chatId, String userStatus) {
        userDao.changeUserStatus(chatId, userStatus);
    }
}
