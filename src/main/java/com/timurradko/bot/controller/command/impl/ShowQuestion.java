package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.constant.UserLevel;
import com.timurradko.bot.controller.constant.UserStatus;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.controller.tool.UiEntityUtil;
import com.timurradko.bot.service.ServiceFactory;
import com.timurradko.bot.service.answer.AnswerService;
import com.timurradko.bot.service.question.QuestionService;
import com.timurradko.bot.shared.entity.Answer;
import com.timurradko.bot.shared.entity.Question;
import com.timurradko.bot.shared.entity.User;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class ShowQuestion implements Command {
    private QuestionService questionService = ServiceFactory.getQuestionService();
    private AnswerService answerService = ServiceFactory.getAnswerService();
    private int i = 0;

    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        User user = session.getUser();
        String userLevel = user.getUserLevel();
        if (userLevel.equals(UserLevel.NOT_REGISTERED_USER)) {
            List<Question> allQuestions = questionService.getAllTestQuestion();
            session.setAllTestQuestion(allQuestions);
            if (i < allQuestions.size()) {
                Question nextQuestion = session.getNextQuestion(i);
                Integer questionId = nextQuestion.getQuestionId();
                String question = nextQuestion.getQuestion();
                ChatUtil.sendMessage(question, chatId, source);

                List<Answer> allAnswers = answerService.getAllAnswers(questionId);
                String answersText = UiEntityUtil.answerForQuestion(allAnswers);
                session.setAllAnswersForQuestion(allAnswers);
                ChatUtil.sendMessage(answersText, chatId, source);
                i++;
                session.setNextCommand(TaskManager.getCommand(CommandNames.CHOOSE_ANSWER));
            } else {
                Command endTesting = TaskManager.getCommand(CommandNames.SHOW_TEST_RESULTS);

                endTesting.execute(update, source);
            }
        } else {
            ChatUtil.sendMessage(GetTested.PASSED_THE_TEST + userLevel, chatId, source);
        }
    }
}
