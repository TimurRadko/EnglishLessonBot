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


public class ChangeCourse implements Command {
    private CourseService courseService = ServiceFactory.getCourseService();
    private static final String CHOOSE_COURSE = "Choose course (Выберите курс):\n\n";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        ChatUtil.sendMessage(CHOOSE_COURSE, update, source);
        List<Course> allActualCourses = courseService.getAllCourses();
        String text = UiEntityUtil.coursesToShortString(allActualCourses);
        ChatUtil.sendMessage(text, update, source);
        UserSession session = SessionManager.getSession(update);
        session.setAllCourses(allActualCourses);
        session.setNextCommand(TaskManager.getCommand(CommandNames.CHOOSE_COURSE));
    }
}
