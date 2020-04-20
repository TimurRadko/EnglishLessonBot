package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.shared.entity.security.Role;
import com.timurradko.bot.shared.entity.User;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.List;

public class ShowMainMenuCommand implements Command {
    private static final String ALL_COURSES = "All Courses";
    private static final String MY_COURSE = "My Course";
    private static final String START_TEST = "Get Tested";
    private static final String MANAGE_USERS = "Manage Users";
    private static final String ALL_TEST_QUESTIONS = "Test Questions";
    private static final String GET_ALL_TEACHERS = "Our Teachers";
    private static final String CHANGE_THE_COURSE = "Change Course";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        List<InlineKeyboardButton> buttons1 = Arrays.asList(
                new InlineKeyboardButton(MY_COURSE).setCallbackData(CommandNames.SHOW_MY_COURSE)
        );
        List<InlineKeyboardButton> buttons2 = Arrays.asList(
                new InlineKeyboardButton(ALL_COURSES).setCallbackData(CommandNames.SHOW_ALL_COURSES)
        );

        List<InlineKeyboardButton> buttons3 = Arrays.asList(
                new InlineKeyboardButton(START_TEST).setCallbackData(CommandNames.GET_TESTED)
        );

        List<InlineKeyboardButton> buttons4 = Arrays.asList(
                new InlineKeyboardButton(GET_ALL_TEACHERS).setCallbackData(CommandNames.SHOW_ALL_TEACHERS)
        );

        List<InlineKeyboardButton> buttons5 = Arrays.asList(
                new InlineKeyboardButton(CHANGE_THE_COURSE).setCallbackData(CommandNames.CHANGE_COURSE)
        );

        List<List<InlineKeyboardButton>> keyboard = Arrays.asList(buttons1, buttons2, buttons3, buttons4, buttons5);

        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        User user = session.getUser();
        Role role = user.getRole();
        if (Role.ADMIN.equals(role)) {
            List<InlineKeyboardButton> buttons6 = Arrays.asList(
                    new InlineKeyboardButton(MANAGE_USERS).setCallbackData(CommandNames.SHOW_USERS_FOR_ADMIN));
            List<InlineKeyboardButton> buttons7 = Arrays.asList(
                    new InlineKeyboardButton(ALL_TEST_QUESTIONS).setCallbackData(CommandNames.SHOW_ALL_TEST_QUESTIONS)
            );
            keyboard = Arrays.asList(buttons1, buttons2, buttons3, buttons4, buttons5, buttons6, buttons7);
        }

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(keyboard);

        String text = StartCommand.SHORT_GREETING + user.getUsername();
        ChatUtil.sendMessageWithMarkup(text, update, source, markup);
    }
}
