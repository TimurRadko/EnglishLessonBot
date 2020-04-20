package com.timurradko.bot.shared.entity;

import com.timurradko.bot.shared.entity.security.Role;

public class User {
    private Long chatId;
    private String username;
    private Integer age;
    private String status;
    private String userLevel;
    private Integer courseId;
    private Role role;

    public User(Long chatId, String username, Integer age, String status, String userLevel, Integer courseId) {
        this.chatId = chatId;
        this.username = username;
        this.age = age;
        this.status = status;
        this.userLevel = userLevel;
        this.courseId = courseId;
        this.role = Role.getByName(status);
    }

    public User() {

    }

    public Long getChatId() {
        return chatId;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public String getStatus() {
        return status;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Role getRole() {
        if (role == null) {
            role = Role.getByName(status);
        }
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
