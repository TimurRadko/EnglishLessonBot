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

public class CreateUserCommand implements Command {
    private UserService userService = ServiceFactory.getUserService();
    private static final String DEFAULT_STATUS = "user";
    private static final String DEFAULT_USER_LEVEL = "not registered";
    private static final String WELCOME = "You registered successfully ended. Welcome to English chatBotLesson.";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        String username = update.getMessage().getText();
        Long chatId = ChatUtil.readChatId(update);
        User newUser = new User(chatId, username, 18, DEFAULT_STATUS, DEFAULT_USER_LEVEL, 6);
        userService.createUser(newUser);
        UserSession session = SessionManager.getSession(chatId);
        if (session != null) {
            session.setUser(newUser);
        } else {
            SessionManager.putSession(chatId, newUser);
        }
        ChatUtil.sendMessage(WELCOME, chatId, source);
        Command showMenu = TaskManager.getCommand(CommandNames.SHOW_MAIN_MENU);
        showMenu.execute(update, source);
    }
}
