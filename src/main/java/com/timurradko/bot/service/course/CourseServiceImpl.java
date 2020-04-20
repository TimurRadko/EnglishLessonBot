package com.timurradko.bot.service.course;

import com.timurradko.bot.dao.DaoFactory;
import com.timurradko.bot.dao.course.CourseDao;
import com.timurradko.bot.shared.entity.Course;

import java.util.*;

public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao = DaoFactory.getCourseDao();

    @Override
    public List<Course> getAllCourses() {
        List<Course> allCourses = courseDao.getAllCoursesShort();
        return allCourses;
    }

    @Override
    public Course getMyCourse(int id) {
        return courseDao.getMyCourseShort(id);
    }

    @Override
    public Course getFullCourse(int id) {
        return courseDao.getFullCourse(id);
    }

}
