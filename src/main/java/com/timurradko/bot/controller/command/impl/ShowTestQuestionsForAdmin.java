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
import com.timurradko.bot.service.question.QuestionService;
import com.timurradko.bot.shared.entity.Question;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class ShowTestQuestionsForAdmin implements Command {
    private QuestionService questionService = ServiceFactory.getQuestionService();

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        List<Question> allTestQuestions = questionService.getAllTestQuestion();
        String text = UiEntityUtil.allTestQuestions(allTestQuestions);
        ChatUtil.sendMessage(text, update, source);
        session.setAllTestQuestion(allTestQuestions);
        session.setNextCommand(TaskManager.getCommand(CommandNames.SHOW_ALL_TEST_QUESTIONS));
    }
}
