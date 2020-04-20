package com.timurradko.bot.dao.course;

import com.timurradko.bot.shared.entity.Course;
import java.util.List;

public interface CourseDao {
    List<Course> getAllCoursesShort();
    Course getMyCourseShort(int id);
    Course getFullCourse(int id);

}
