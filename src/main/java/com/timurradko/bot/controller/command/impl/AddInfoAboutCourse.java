package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.course.CourseService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AddInfoAboutCourse implements Command {
    private CourseService courseService = ServiceFactory.getCourseService();

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        String courseDescription = update.getMessage().getText();
        session.setCourseDescription(courseDescription);
        String newCourseTitle = session.getCourseTitle();
        String newCourseDescription = session.getCourseDescription();
        courseService.addNewCourse(newCourseTitle, newCourseDescription);
    }
}
