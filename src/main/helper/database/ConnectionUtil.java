package main.helper.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;

public class ConnectionUtil {
    private static HikariDataSource dataSource;
    static {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3308/project_oop");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("");

        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setIdleTimeout(60000);
        hikariConfig.setMaxLifetime(10*60000);

        dataSource = new HikariDataSource(hikariConfig);
    }

    public static HikariDataSource getDataSource(){
        return dataSource;
    }
}