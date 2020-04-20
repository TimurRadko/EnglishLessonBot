package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.controller.tool.UiEntityUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.user.UserService;
import com.timurradko.bot.shared.entity.User;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class ShowUsersForAdmin implements Command {
    private UserService service = ServiceFactory.getUserService();

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        List<User> allUsers = service.getAllUsers(chatId);
        session.setAllUsers(allUsers);
        String usersToString = UiEntityUtil.usersToString(allUsers);
        ChatUtil.sendMessage(usersToString, update, source);
    }
}
