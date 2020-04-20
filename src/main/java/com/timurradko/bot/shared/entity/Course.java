package com.timurradko.bot.shared.entity;

import java.util.Objects;

public class Course {
    private Integer courseId;
    private String title;
    private String description;

    public Course() {
    }

    public Course(Integer courseId, String title, String description) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        if (this.courseId != null) {
            throw new RuntimeException("course_id is already exist");
        }
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.getCourseId()) &&
                Objects.equals(title, course.getTitle()) &&
                Objects.equals(description, course.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, title, description);
    }
}
