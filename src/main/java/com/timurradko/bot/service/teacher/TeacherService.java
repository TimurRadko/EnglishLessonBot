package com.timurradko.bot.service.teacher;

import com.timurradko.bot.shared.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher getTeacher(Integer teacherId);
    List<Teacher> getAllTeachers();
}
