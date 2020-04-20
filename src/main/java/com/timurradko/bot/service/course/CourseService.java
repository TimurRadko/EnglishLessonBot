package com.timurradko.bot.service.course;

import com.timurradko.bot.shared.entity.Course;
import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getMyCourse(int id);
    Course getFullCourse(int id);
}
