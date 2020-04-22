package com.timurradko.bot.controller;

import com.timurradko.bot.controller.base.SessionManager;
import com.timurradko.bot.controller.base.UserSession;
import com.timurradko.bot.controller.command.Command;
import com.timurradko.bot.controller.command.impl.*;
import com.timurradko.bot.controller.constant.CommandNames;
import com.timurradko.bot.controller.exception.UnknownCommandException;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private static final Map<String, Command> COMMANDS = new HashMap<>();

    TaskManager() {
        COMMANDS.put(CommandNames.START, new StartCommand());
        COMMANDS.put(CommandNames.NEW_USER, new CreateUserCommand());
        COMMANDS.put(CommandNames.SHOW_MAIN_MENU, new ShowMainMenuCommand());
        COMMANDS.put(CommandNames.SHOW_ALL_COURSES, new ShowAllCourses());
        COMMANDS.put(CommandNames.SHOW_FULL_INFORMATION, new GetFullCourseInfo());
        COMMANDS.put(CommandNames.SHOW_USERS_FOR_ADMIN, new ShowUsersForAdmin());
        COMMANDS.put(CommandNames.GET_TESTED, new GetTested());
        COMMANDS.put(CommandNames.SHOW_QUESTION, new ShowQuestion());
        COMMANDS.put(CommandNames.SHOW_ALL_TEST_QUESTIONS, new ShowTestQuestionsForAdmin());
        COMMANDS.put(CommandNames.CHOOSE_ANSWER, new ChooseAnswer());
        COMMANDS.put(CommandNames.SHOW_MY_COURSE, new ShowMyCourse());
        COMMANDS.put(CommandNames.SHOW_TEST_RESULTS, new ShowTestResults());
        COMMANDS.put(CommandNames.CHANGE_USER_LEVEL, new ChangeUserLevel());
        COMMANDS.put(CommandNames.CHANGE_USER_STATUS, new ChangeUserStatus());
        COMMANDS.put(CommandNames.SHOW_ALL_TEACHERS, new ShowAllTeachers());
        COMMANDS.put(CommandNames.CHANGE_COURSE, new ChangeCourse());
        COMMANDS.put(CommandNames.CHOOSE_COURSE, new ChooseCourse());
        COMMANDS.put(CommandNames.EDIT_USER, new EditUser());
        COMMANDS.put(CommandNames.CHANGES_CHOOSE, new ChangesChoose());
        COMMANDS.put(CommandNames.ADD_NEW_COURSE, new AddNewCourse());
        COMMANDS.put(CommandNames.ADMIN_CHANGE_COURSE, new AdminChangeCourse());
        COMMANDS.put(CommandNames.EDIT_COURSE, new EditCourse());
        COMMANDS.put(CommandNames.DELETE_COURSE, new DeleteCourse());
        COMMANDS.put(CommandNames.CHANGE_PARAMETERS_COURSE, new ChangeParametersCourse());
        COMMANDS.put(CommandNames.READ_COURSE_TITLE, new ReadCourseTitle());
        COMMANDS.put(CommandNames.READ_COURSE_DESCRIPTION, new ReadCourseDescription());
        COMMANDS.put(CommandNames.ADD_INFO_ABOUT_COURSE, new AddInfoAboutCourse());
        COMMANDS.put(CommandNames.CHOOSE_COURSE_ACTION, new ChooseCourseAction());
        COMMANDS.put(CommandNames.SELECT_COURSE_TO_DELETE, new SelectCourseToDelete());
    }

    public void impl(String commandName, Update update, EnglishLessonBot source) throws TelegramApiException {
        Command command = COMMANDS.get(commandName);
        UserSession session = SessionManager.getSession(update);
        if (command != null) {
            if (session != null) {
                session.clearNextCommandIfExists();
            }
            command.execute(update, source);
        } else {
            Command nextCommand = session.getNextCommand();
            if (nextCommand != null) {
                nextCommand.execute(update, source);
            } else {
                throw new UnknownCommandException();
            }
        }
    }

    public static Command getCommand(String name) {
        return COMMANDS.get(name);
    }
}
