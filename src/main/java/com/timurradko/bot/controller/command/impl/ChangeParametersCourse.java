package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.MessageForAdmin;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.shared.entity.Course;
import com.timurradko.bot.shared.entity.User;
import com.timurradko.bot.shared.entity.security.Feature;
import com.timurradko.bot.shared.exception.PermissionDeniedException;
import com.timurradko.bot.shared.tool.SecurityUtil;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class ChangeParametersCourse implements Command {
    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        User user = session.getUser();

        //Added for Permission
        if (!SecurityUtil.hasFeature(user, Feature.CHANGE_PARAMETERS_COURSE)) {
            throw new PermissionDeniedException();
        }

        List<Course> allCourses =  session.getAllCourses();
        ChatUtil.sendMessage(MessageForAdmin.WE_ARE_WORKING_ON_IT, chatId, source);
    }
}
