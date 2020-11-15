package me.devksh930.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class PostgresqlRunner implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(PostgresqlRunner.class);

    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            logger.info(String.valueOf(dataSource.getClass()));
            logger.info(connection.getMetaData().getDriverName());
            String url = connection.getMetaData().getURL();
            String name = connection.getMetaData().getUserName();
            logger.info(url);
            logger.info(name);

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE users(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
//
            statement.executeUpdate(sql);
        }
        jdbcTemplate.execute("INSERT INTO users(ID,name) VALUES(1,'sungho')");

    }
}
