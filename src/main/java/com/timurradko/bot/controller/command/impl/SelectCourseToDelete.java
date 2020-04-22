package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.controller.tool.UiEntityUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.course.CourseService;
import com.timurradko.bot.shared.entity.Course;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class SelectCourseToDelete implements Command {
    private CourseService courseService = ServiceFactory.getCourseService();
    private static final String SELECT_COURSE_TO_DELETE = "Please, select the course to delete\n";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        ChatUtil.sendMessage(SELECT_COURSE_TO_DELETE, chatId, source);
        List<Course> allCourses =  courseService.getAllCourses();
        String stringAllCourses = UiEntityUtil.coursesToShortString(allCourses);
        ChatUtil.sendMessage(stringAllCourses, chatId, source);
        session.setNextCommand(TaskManager.getCommand(CommandNames.DELETE_COURSE));
    }
}
