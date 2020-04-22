package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.constant.MessageForAdmin;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.controller.tool.UiEntityUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.user.UserService;
import com.timurradko.bot.shared.entity.User;
import com.timurradko.bot.shared.entity.security.Feature;
import com.timurradko.bot.shared.exception.PermissionDeniedException;
import com.timurradko.bot.shared.tool.SecurityUtil;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class EditUser implements Command {
    private UserService userService = ServiceFactory.getUserService();

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        User user = session.getUser();

        //Added for Permission
        if (!SecurityUtil.hasFeature(user, Feature.EDIT_USER)) {
            throw new PermissionDeniedException();
        }

        ChatUtil.sendMessage(MessageForAdmin.AVAILABLE_FIELDS, chatId, source);
        List<User> allUsers = session.getAllUsers();
        if (allUsers == null) {
            allUsers = userService.getAllUsers(chatId);
        }
        String input = update.getMessage().getText();
        input = input.substring(1);
        int userNumber = Integer.parseInt(input);
        user = allUsers.get(userNumber - 1);

        session.setIdForAdmin(user.getChatId());

        String usersToString = UiEntityUtil.userToString(user);
        ChatUtil.sendMessage(usersToString, chatId, source);

        session.setNextCommand(TaskManager.getCommand(CommandNames.CHANGES_CHOOSE));
    }
}
