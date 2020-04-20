package com.timurradko.bot.dao.config;

import java.util.Objects;

public abstract class Environment {
    protected String url;
    protected String username;
    protected String password;
    protected String driver;
    protected int poolSize;

    protected Environment (boolean initImmediate) {
        if (initImmediate) {
            init();
        }
    }

    abstract void init();

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    public int getPoolSize() {
        return poolSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Environment that = (Environment) o;
        return Objects.equals(url, that.getUrl()) &&
                Objects.equals(username, that.getUsername()) &&
                Objects.equals(password, that.getPassword()) &&
                Objects.equals(driver, that.getDriver()) &&
                Objects.equals(poolSize, that.getPoolSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, username, password, driver, poolSize);
    }

    @Override
    public String toString() {
        return "Environment{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", driver='" + driver + '\'' +
                ", poolSize=" + poolSize +
                '}';
    }
}
