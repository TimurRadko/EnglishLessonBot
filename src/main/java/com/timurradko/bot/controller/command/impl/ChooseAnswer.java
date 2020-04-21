package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.exception.UnknownCommandException;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.shared.entity.Answer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class ChooseAnswer implements Command {
    private static final String TRUE = "true";
    private Integer userLevel = 0;

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        List<Answer> allAnswers = session.getAllAnswerForQuestion();

        String input = update.getMessage().getText();
        input = input.substring(1);
        try {
            int answerNumber = Integer.parseInt(input);
            Answer answer = allAnswers.get(answerNumber - 1);
            String correctness = answer.getCorrectness();
            if (correctness.equals(TRUE)) {
                session.setUserLevel(userLevel++);
            }

            Command showQuestion = TaskManager.getCommand(CommandNames.SHOW_QUESTION);
            showQuestion.execute(update, source);
        } catch (NumberFormatException e) {
            throw new UnknownCommandException();
        }
    }
}
