package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.user.UserService;
import com.timurradko.bot.shared.entity.User;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand implements Command {
    private UserService userService = ServiceFactory.getUserService();
    private static final String GREETING = "Hi, what is your name? (Привет, как вас зовут?)";
    static final String SHORT_GREETING = "Hello, ";
    static final String DEFAULT_USER_LEVEL = "not registered";
    private static final String DEFAULT_USER_STATUS = "user";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        User user = userService.getUser(chatId);
        UserSession session = SessionManager.getSession(chatId);
        if (user != null) {
            if (session != null) {
                session.setUser(user);
            } else {
                SessionManager.putSession(chatId, user);
            }
            Command showMenu = TaskManager.getCommand(CommandNames.SHOW_MAIN_MENU);
            showMenu.execute(update, source);
        } else if (session == null) {
            ChatUtil.sendMessage(GREETING, chatId, source);
            Command newUserCommand = TaskManager.getCommand(CommandNames.NEW_USER);
            session = SessionManager.putSession(chatId, new User(chatId, null, 18, DEFAULT_USER_STATUS, DEFAULT_USER_LEVEL, 6));
            session.setNextCommand(newUserCommand);
        }
    }
}
