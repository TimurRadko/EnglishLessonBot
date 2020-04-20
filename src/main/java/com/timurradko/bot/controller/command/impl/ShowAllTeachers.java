package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.controller.tool.UiEntityUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.teacher.TeacherService;
import com.timurradko.bot.shared.entity.Teacher;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class ShowAllTeachers implements Command {
    private TeacherService teacherService = ServiceFactory.getTeacherService();

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        List<Teacher> allTeachers = teacherService.getAllTeachers();
        String text = UiEntityUtil.showAllTeachers(allTeachers);
        ChatUtil.sendMessage(text, update, source);
    }
}
