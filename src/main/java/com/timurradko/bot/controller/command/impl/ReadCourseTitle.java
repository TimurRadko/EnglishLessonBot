package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.constant.MessageForAdmin;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.shared.entity.User;
import com.timurradko.bot.shared.entity.security.Feature;
import com.timurradko.bot.shared.exception.PermissionDeniedException;
import com.timurradko.bot.shared.tool.SecurityUtil;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ReadCourseTitle implements Command {
    private static final String ENTER_COURSE_DESCRIPTION = "Enter course description:\n";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        User user = session.getUser();

        //Added for Permission
        if (!SecurityUtil.hasFeature(user, Feature.READ_COURSE_TITLE)) {
            throw new PermissionDeniedException();
        }
        String courseTitle = update.getMessage().getText();
        if (courseTitle.startsWith(MessageForAdmin.COMMAND)) {
            Command command = TaskManager.getCommand(CommandNames.START);
            ChatUtil.sendMessage(MessageForAdmin.ERROR_COMMAND, chatId, source);
            command.execute(update, source);
        } else {
            session.setCourseTitle(courseTitle);
            ChatUtil.sendMessage(ENTER_COURSE_DESCRIPTION, chatId, source);
            session.setNextCommand(TaskManager.getCommand(CommandNames.READ_COURSE_DESCRIPTION));
        }
    }
}
