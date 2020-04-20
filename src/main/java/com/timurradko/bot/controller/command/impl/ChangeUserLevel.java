package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.user.UserService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ChangeUserLevel implements Command {
    private UserService userService = ServiceFactory.getUserService();
    private static final String WE_ARE_WORKING_ON_IT = "Sorry! We are working on it";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        ChatUtil.sendMessage(WE_ARE_WORKING_ON_IT, chatId, source);
    }
}
