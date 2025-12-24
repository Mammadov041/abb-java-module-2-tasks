package org.abb_tech.task17and19Db.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.abb_tech.task17and19Db.exception.DBConnectionException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConfig {
    private static final DataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/car");
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername("postgres");
        config.setPassword("postgres");
        config.setSchema("public");
        config.setMinimumIdle(2);
        config.setIdleTimeout(40000);
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(10000);
        config.setKeepaliveTime(30000);
        config.setAutoCommit(true);
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException connectionException) {
            throw new DBConnectionException("Could not connect to DB", connectionException);
        }
    }

}
