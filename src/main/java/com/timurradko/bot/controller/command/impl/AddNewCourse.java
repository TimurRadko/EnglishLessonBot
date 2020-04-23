package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.shared.entity.User;
import com.timurradko.bot.shared.entity.security.Feature;
import com.timurradko.bot.shared.exception.PermissionDeniedException;
import com.timurradko.bot.shared.tool.SecurityUtil;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class AddNewCourse implements Command {
    private static final String ENTER_COURSE_TITLE = "Enter course title:\n";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        User user = session.getUser();

        //Added for Permission
        if (!SecurityUtil.hasFeature(user, Feature.ADD_NEW_COURSE)) {
            throw new PermissionDeniedException();
        }

        ChatUtil.sendMessage(ENTER_COURSE_TITLE, chatId, source);
        session.setNextCommand(TaskManager.getCommand(CommandNames.READ_COURSE_TITLE));
    }
}
