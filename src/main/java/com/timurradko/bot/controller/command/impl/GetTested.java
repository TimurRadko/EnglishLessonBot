package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.tool.ChatUtil;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class GetTested implements Command {
    private static final String TEST_EXPLANATION = "Choose the correct answer (Выберите правильный ответ):\n\n";
    private static final String PASSED_THE_TEST = "You have already passed the test. Your level of English:   ";

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        ChatUtil.sendMessage(GetTested.TEST_EXPLANATION, update, source);
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        String userLevel = session.getUser().getUserLevel();
        if (userLevel.equals(StartCommand.DEFAULT_USER_LEVEL)) {
            Command showQuestion = TaskManager.getCommand(CommandNames.SHOW_QUESTION);
            session.setNextCommand(showQuestion);
            showQuestion.execute(update, source);
        } else {
            ChatUtil.sendMessage(PASSED_THE_TEST + userLevel, chatId, source);
            Command showMenu = TaskManager.getCommand(CommandNames.SHOW_MAIN_MENU);
            showMenu.execute(update, source);
        }

    }
}
