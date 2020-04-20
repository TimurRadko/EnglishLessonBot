package com.timurradko.bot.shared.exception;

public class PermissionDeniedException extends UserFriendlyException {
    public PermissionDeniedException() {
        super("Permission denied");
    }
}
