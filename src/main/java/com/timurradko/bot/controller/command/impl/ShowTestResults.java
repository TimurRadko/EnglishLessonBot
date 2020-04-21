package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.UserLevel;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.user.UserService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ShowTestResults implements Command {
    private UserService userService = ServiceFactory.getUserService();
    private static final String TEXT = "Congratulations, you have passed the test. Your english level: ";
    private static final String PREDATOR = "What are you?";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        Integer userLevel = session.getUserLevel();
        switch (userLevel) {
            case 0:
                ChatUtil.sendMessage(TEXT + UserLevel.BEGINNER, chatId, source);
                userService.changeUserLevel(chatId, UserLevel.BEGINNER);
                break;
            case 1:
                ChatUtil.sendMessage(TEXT + UserLevel.ELEMENTARY, chatId, source);
                userService.changeUserLevel(chatId, UserLevel.ELEMENTARY);
                break;
            case 2:
                ChatUtil.sendMessage(TEXT + UserLevel.PRE_INTERMEDIATE, chatId, source);
                userService.changeUserLevel(chatId, UserLevel.PRE_INTERMEDIATE);
                break;
            case 3:
                ChatUtil.sendMessage(TEXT + UserLevel.INTERMEDIATE, chatId, source);
                userService.changeUserLevel(chatId, UserLevel.INTERMEDIATE);
                break;
            case 4:
                ChatUtil.sendMessage(TEXT + UserLevel.UPPER_INTERMEDIATE, chatId, source);
                userService.changeUserLevel(chatId, UserLevel.UPPER_INTERMEDIATE);
                break;
            case 5:
                ChatUtil.sendMessage(TEXT + UserLevel.ADVANCED, chatId, source);
                userService.changeUserLevel(chatId, UserLevel.ADVANCED);
                break;
            default:
                ChatUtil.sendMessage(PREDATOR, chatId, source);
        }
    }
}
