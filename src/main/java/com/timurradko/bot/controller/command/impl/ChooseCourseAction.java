package com.timurradko.bot.controller.command.impl;

import com.timurradko.bot.controller.EnglishLessonBot;
import com.timurradko.bot.controller.TaskManager;
import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.constant.MessageForAdmin;
import com.timurradko.bot.controller.tool.ChatUtil;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ChooseCourseAction implements Command {
    @Override
    public void execute(Update update, EnglishLessonBot source) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        Command command;
        String input = update.getMessage().getText();
        input = input.substring(1);
        int chooseNumber = Integer.parseInt(input);
        switch (chooseNumber) {
            case 1:
                command = TaskManager.getCommand(CommandNames.ADD_NEW_COURSE);
                command.execute(update, source);
                break;
            case 2:
                command = TaskManager.getCommand(CommandNames.EDIT_COURSE);
                command.execute(update, source);
                break;
            case 3:
                command = TaskManager.getCommand(CommandNames.SELECT_COURSE_TO_DELETE);
                command.execute(update, source);
                break;
            default:
                ChatUtil.sendMessage(MessageForAdmin.UNKNOWN_ERROR, chatId, source);
        }
    }
}
