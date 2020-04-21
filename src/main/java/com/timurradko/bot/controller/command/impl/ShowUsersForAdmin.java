package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.controller.tool.UiEntityUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.user.UserService;
import com.timurradko.bot.shared.entity.User;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class ShowUsersForAdmin implements Command {
    private UserService userService = ServiceFactory.getUserService();
    private static final String CHOOSE_USER = "Which user data to edit:\n\n";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        List<User> allUsers = userService.getAllUsers(chatId);
        ChatUtil.sendMessage(CHOOSE_USER, chatId, source);
        session.setAllUsers(allUsers);
        String usersToString = UiEntityUtil.userToString(allUsers);
        ChatUtil.sendMessage(usersToString, update, source);
        session.setNextCommand(TaskManager.getCommand(CommandNames.EDIT_USER));
    }
}
