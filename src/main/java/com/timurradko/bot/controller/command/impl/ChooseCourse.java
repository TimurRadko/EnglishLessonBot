package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.course.CourseService;
import com.timurradko.bot.service.user.UserService;
import com.timurradko.bot.shared.entity.Course;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ChooseCourse implements Command {
    private UserService userService = ServiceFactory.getUserService();
    private CourseService courseService = ServiceFactory.getCourseService();
    private static final String YOUR_CHOOSE = "You choose:   ";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        String input = update.getMessage().getText();
        input = input.substring(1);
        try {
            int courseNumber = Integer.parseInt(input);
            userService.chooseTheCourse(chatId, courseNumber);
            Integer courseId = userService.getUser(chatId).getCourseId();
            Course course = courseService.getMyCourse(courseId);
            ChatUtil.sendMessage(YOUR_CHOOSE + course.getTitle(), update, source);
        } catch (NumberFormatException e) {
            Command showQuestion = TaskManager.getCommand(CommandNames.CHANGE_COURSE);
            showQuestion.execute(update, source);
        }

    }
}
