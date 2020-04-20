package com.timurradko.bot.service.teacher;

import com.timurradko.bot.dao.DaoFactory;
import com.timurradko.bot.dao.teachers.TeacherDao;
import com.timurradko.bot.shared.entity.Teacher;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private TeacherDao teacherDao = DaoFactory.getTeacherDao();

    @Override
    public Teacher getTeacher(Integer teacherId) {
        Teacher teacher = teacherDao.getTeacher(teacherId);
        return teacher;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> allTeachers = teacherDao.getAllTeachers();
        return allTeachers;
    }
}
