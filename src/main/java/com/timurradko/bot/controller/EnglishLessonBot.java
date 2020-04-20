package com.timurradko.bot.controller;

import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.exception.UnknownCommandException;
import com.timurradko.bot.controller.tool.ChatUtil;
import com.timurradko.bot.shared.entity.User;
import com.timurradko.bot.shared.entity.security.Role;
import com.timurradko.bot.shared.exception.UserFriendlyException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class EnglishLessonBot extends TelegramLongPollingBot {
    private final TaskManager TASK_MANAGER = new TaskManager();
    private static final String SERVER_ERROR = "Server error";
    private static final String MESSAGE_FOR_BLOCKED_USER = "You are blocked. Contact your administrator for an explanation.";

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (!preliminaryCheck(update)) {
                return;
            }
            String input = getInput(update);
            TASK_MANAGER.impl(input, update, this);
        } catch (UserFriendlyException e) {
            e.printStackTrace();
            try {
                ChatUtil.sendMessage(e.getMessage(), update, this);
            } catch (TelegramApiException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                ChatUtil.sendMessage(SERVER_ERROR, update, this);
            } catch (TelegramApiException ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean preliminaryCheck(Update update) throws TelegramApiException {
        if (!sessionOk(update)) {
            Command command = TaskManager.getCommand(CommandNames.START);
            command.execute(update, this);
            return false;
        }
        return checkPermission(update);
    }

    private boolean checkPermission(Update update) throws TelegramApiException {
        UserSession session = SessionManager.getSession(update);
        User user = session.getUser();
        if (Role.BLOCKED.equals(user.getRole())) {
            ChatUtil.sendMessage(MESSAGE_FOR_BLOCKED_USER, update, this);
            return false;
        }
        return true;
    }

    private boolean sessionOk(Update update) {
        UserSession session = SessionManager.getSession(update);
        return session != null;
    }

    private String getInput(Update update) {
        String input = null;
        if (update.hasMessage()) {
            input = update.getMessage().getText();
        } else if (update.hasCallbackQuery()) {
            input = update.getCallbackQuery().getData();
        } else {
            throw new UnknownCommandException();
        }
        return input;
    }

    @Override
    public String getBotUsername() {
        return "EnglishTeBot";
    }

    @Override
    public String getBotToken() {
        return "1189923546:AAGakWmZRQ1aN1SdVAzAr2--QeAxDp06OAk";
    }

    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            ApiContextInitializer.init();
            telegramBotsApi.registerBot(new EnglishLessonBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
