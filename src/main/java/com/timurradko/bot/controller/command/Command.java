package com.timurradko.bot.controller.command;

import com.timurradko.bot.controller.EnglishLessonBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Command {
    void execute(Update update, EnglishLessonBot source) throws TelegramApiException;
}
