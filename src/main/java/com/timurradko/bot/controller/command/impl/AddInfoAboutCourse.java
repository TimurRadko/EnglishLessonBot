package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.constant.MessageForAdmin;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.course.CourseService;
import com.timurradko.bot.shared.entity.User;
import com.timurradko.bot.shared.entity.security.Feature;
import com.timurradko.bot.shared.exception.PermissionDeniedException;
import com.timurradko.bot.shared.tool.SecurityUtil;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AddInfoAboutCourse implements Command {
    private CourseService courseService = ServiceFactory.getCourseService();
    private static final String COURSE_ADDED = "The course added";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        User user = session.getUser();

        //Added for Permission
        if (!SecurityUtil.hasFeature(user, Feature.ADD_INFO_ABOUT_COURSE)) {
            throw new PermissionDeniedException();
        }

        String courseDescription = update.getMessage().getText();
        String checkText = courseDescription.substring(1);
        if (checkText.equals(MessageForAdmin.COMMAND)) {
            Command command = TaskManager.getCommand(CommandNames.START);
            ChatUtil.sendMessage(MessageForAdmin.ERROR_COMMAND, chatId, source);
            command.execute(update, source);
        } else {
            session.setCourseDescription(courseDescription);
            String newCourseTitle = session.getCourseTitle();
            if (newCourseTitle != null) {
                String newCourseDescription = session.getCourseDescription();
                courseService.addNewCourse(newCourseTitle, newCourseDescription);
                ChatUtil.sendMessage(COURSE_ADDED, chatId, source);
            } else {
                Command command = TaskManager.getCommand(CommandNames.START);
                ChatUtil.sendMessage(MessageForAdmin.ERROR_COMMAND, chatId, source);
                command.execute(update, source);
            }
        }
    }
}
