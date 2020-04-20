package com.timurradko.bot.dao.teachers;

import com.timurradko.bot.shared.entity.Teacher;

import java.util.List;

public interface TeacherDao {
    Teacher getTeacher(Integer teacherId);
    List<Teacher> getAllTeachers();
}
