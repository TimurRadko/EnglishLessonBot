package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.MessageForAdmin;
import com.timurradko.bot.controller.constant.UserLevel;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.user.UserService;
import com.timurradko.bot.shared.entity.User;
import com.timurradko.bot.shared.entity.security.Feature;
import com.timurradko.bot.shared.exception.PermissionDeniedException;
import com.timurradko.bot.shared.tool.SecurityUtil;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class ChangeUserLevel implements Command {
    private UserService userService = ServiceFactory.getUserService();

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        User user = session.getUser();

        //Added for Permission
        if (!SecurityUtil.hasFeature(user, Feature.CHANGE_USER_LEVEL)) {
            throw new PermissionDeniedException();
        }

        String input = update.getMessage().getText();
        input = input.substring(1);
        int levelNumber = Integer.parseInt(input);

        Long chatIdFromAdmin = session.getIdForAdmin();

        switch (levelNumber) {
            case 1:
                userService.changeUserLevel(chatIdFromAdmin, UserLevel.BEGINNER);
                break;
            case 2:
                userService.changeUserLevel(chatIdFromAdmin, UserLevel.ELEMENTARY);
                break;
            case 3:
                userService.changeUserLevel(chatIdFromAdmin, UserLevel.PRE_INTERMEDIATE);
                break;
            case 4:
                userService.changeUserLevel(chatIdFromAdmin, UserLevel.INTERMEDIATE);
                break;
            case 5:
                userService.changeUserLevel(chatIdFromAdmin, UserLevel.UPPER_INTERMEDIATE);
                break;
            case 6:
                userService.changeUserLevel(chatIdFromAdmin, UserLevel.ADVANCED);
                break;
            default:
                ChatUtil.sendMessage(MessageForAdmin.UNKNOWN_ERROR, chatId, source);
        }
        String userLevel = userService.getUser(chatIdFromAdmin).getUserLevel();
        ChatUtil.sendMessage(MessageForAdmin.NEW_USER_LEVEL + userLevel, chatId, source);
    }
}
