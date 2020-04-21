package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.controller.tool.UiEntityUtil;
import com.timurradko.bot.shared.entity.User;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class EditUser implements Command {
    private static final String AVAILABLE_FIELDS = "Select field to change: ";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        ChatUtil.sendMessage(AVAILABLE_FIELDS, chatId, source);
        List<User> allUsers = session.getAllUsers();

        String input = update.getMessage().getText();
        input = input.substring(1);
        int userNumber = Integer.parseInt(input);
        User user = allUsers.get(userNumber - 1);

        session.setIdForAdmin(user.getChatId());

        String usersToString = UiEntityUtil.userToString(user);
        ChatUtil.sendMessage(usersToString, chatId, source);

        session.setNextCommand(TaskManager.getCommand(CommandNames.CHANGES_CHOOSE));
    }
}
