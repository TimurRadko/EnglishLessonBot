package com.timurradko.bot.shared.entity;

public class Teacher {
    private Integer teacherId;
    private String teacherName;
    private String primaryLanguage;
    private String startLevel;
    private Integer courseId;

    public Teacher() {}

    public Teacher(Integer teacherId, String teacherName, String primaryLanguage, String startLevel, Integer courseId) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.primaryLanguage = primaryLanguage;
        this.startLevel = startLevel;
        this.courseId = courseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public String getStartLevel() {
        return startLevel;
    }

    public void setStartLevel(String startLevel) {
        this.startLevel = startLevel;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
