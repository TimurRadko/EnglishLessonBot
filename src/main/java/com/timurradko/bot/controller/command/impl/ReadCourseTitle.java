package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.tool.ChatUtil;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ReadCourseTitle implements Command {
    private static final String ENTER_COURSE_DESCRIPTION = "Enter course description:\n";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        String courseTitle = update.getMessage().getText();
        session.setCourseTitle(courseTitle);
        ChatUtil.sendMessage(ENTER_COURSE_DESCRIPTION, chatId, source);
        session.setNextCommand(TaskManager.getCommand(CommandNames.READ_COURSE_DESCRIPTION));

//        Command readCourseDescription = TaskManager.getCommand(CommandNames.READ_COURSE_DESCRIPTION);
//        readCourseDescription.execute(update, source);
    }
}
