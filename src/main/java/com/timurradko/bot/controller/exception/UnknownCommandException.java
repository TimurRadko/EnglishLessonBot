package com.timurradko.bot.controller.exception;

import com.timurradko.bot.shared.exception.UserFriendlyException;

public class UnknownCommandException extends UserFriendlyException {
    public UnknownCommandException() {
        super("Unknown command name");
    }
}
