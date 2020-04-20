package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.controller.tool.UiEntityUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.course.CourseService;
import com.timurradko.bot.service.user.UserService;
import com.timurradko.bot.shared.entity.Course;
import com.timurradko.bot.shared.entity.User;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ShowMyCourse implements Command {
    private UserService userService = ServiceFactory.getUserService();
    private CourseService courseService = ServiceFactory.getCourseService();

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        User user = userService.getUser(chatId);
        Course course = courseService.getMyCourse(user.getCourseId());
        String fullCourseMessage = UiEntityUtil.coursesToString(course);
        ChatUtil.sendMessage(fullCourseMessage, chatId, source);
    }
}
