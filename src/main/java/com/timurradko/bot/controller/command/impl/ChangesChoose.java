package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.constant.UserLevel;
import com.timurradko.bot.controller.constant.UserStatus;
import com.timurradko.bot.controller.exception.UnknownCommandException;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.controller.tool.UiEntityUtil;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.List;


public class ChangesChoose implements Command {
    private static final String CHOOSE_USER_LEVEL = "Choose user level:\n\n";
    private static final String CHOOSE_USER_STATUS = "Choose user status:\n\n";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);

        String input = update.getMessage().getText();
        input = input.substring(1);

        try {
            int chooseNumber = Integer.parseInt(input);
            if (chooseNumber == 1) {
                ChatUtil.sendMessage(CHOOSE_USER_STATUS, chatId, source);
                List<String> userStatus = Arrays.asList(UserStatus.BLOCKED, UserStatus.ADMIN, UserStatus.USER);
                String userStatusToString = UiEntityUtil.valueToString(userStatus);
                ChatUtil.sendMessage(userStatusToString, chatId, source);
                session.setNextCommand(TaskManager.getCommand(CommandNames.CHANGE_USER_STATUS));
            } else {
                ChatUtil.sendMessage(CHOOSE_USER_LEVEL, chatId, source);
                List<String> userLevels = Arrays.asList(UserLevel.BEGINNER,
                        UserLevel.ELEMENTARY,
                        UserLevel.PRE_INTERMEDIATE,
                        UserLevel.INTERMEDIATE,
                        UserLevel.UPPER_INTERMEDIATE,
                        UserLevel.ADVANCED);
                String userLevelToString = UiEntityUtil.valueToString(userLevels);
                ChatUtil.sendMessage(userLevelToString, chatId, source);
                session.setNextCommand(TaskManager.getCommand(CommandNames.CHANGE_USER_LEVEL));
            }
        } catch (NumberFormatException e) {
            throw new UnknownCommandException();
        }
    }
}
