package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.controller.tool.UiEntityUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.course.CourseService;
import com.timurradko.bot.shared.entity.Course;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class GetFullCourseInfo implements Command {
    private CourseService courseService = ServiceFactory.getCourseService();

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        List<Course> allCourses = session.getAllCourses();
        if (allCourses == null) {
            allCourses = courseService.getAllCourses();
        }
        String input = update.getMessage().getText();
        input = input.substring(1);
        int courseNumber = Integer.parseInt(input);
        Course course = allCourses.get(courseNumber - 1);

        Course fullCourse = courseService.getFullCourse(course.getCourseId());
        String fullCourseMessage = UiEntityUtil.coursesToString(fullCourse);
        ChatUtil.sendMessage(fullCourseMessage, chatId, source);

    }
}
