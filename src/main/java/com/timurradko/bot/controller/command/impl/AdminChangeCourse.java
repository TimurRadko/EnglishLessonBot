package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.constant.UserLevel;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.controller.tool.UiEntityUtil;
import com.timurradko.bot.shared.entity.User;
import com.timurradko.bot.shared.entity.security.Role;
import com.timurradko.bot.shared.exception.PermissionDeniedException;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.List;

public class AdminChangeCourse implements Command {
    private static final String ADMIN_ADD_NEW_COURSE = "Add New Course";
    private static final String ADMIN_DELETE_COURSE = "Delete Course";
    private static final String ADMIN_CHANGE_PARAMETERS_COURSE = "Change Parameters of Course";
    private static final String SELECT_YOU_ACTION = "Select your action\n";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        User user = session.getUser();
        Role role = user.getRole();

        if (Role.ADMIN.equals(role)) {
            List<String> changeOptionsOfCourses = Arrays.asList(
                    ADMIN_ADD_NEW_COURSE,
                    ADMIN_CHANGE_PARAMETERS_COURSE,
                    ADMIN_DELETE_COURSE);
            ChatUtil.sendMessage(SELECT_YOU_ACTION, chatId, source);
            String options = UiEntityUtil.valueToString(changeOptionsOfCourses);
            ChatUtil.sendMessage(options, chatId, source);
            session.setNextCommand(TaskManager.getCommand(CommandNames.CHOOSE_COURSE_ACTION));
        } else {
            throw new PermissionDeniedException();
        }
    }
}
