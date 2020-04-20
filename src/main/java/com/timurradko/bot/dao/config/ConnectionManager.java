package com.timurradko.bot.dao.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
    private static ConnectionPool POOL = new ConnectionPool(new JdbcMySQLEnvironment(true));

    public static Connection take() {
        return POOL.take();
    }

    public static void close(Statement statement, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeOrThrow(Statement statement, Connection connection) {
        close(statement,connection);
    }
}
