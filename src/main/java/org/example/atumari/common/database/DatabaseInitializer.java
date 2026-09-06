package org.example.atumari.common.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Spring Boot가 애플리케이션을 시작할 때 database.sql을 자동으로 실행합니다.
 * 스키마가 없어도 DB_SERVER_URL로 MySQL 서버에 먼저 접속해 생성할 수 있습니다.
 */
@Component
public class DatabaseInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);
    private static final String DATABASE_SCRIPT = "db/database.sql";

    public DatabaseInitializer(Environment environment) {
        DBConnection.configure(
                environment.getProperty("atumari.database.server-url"),
                environment.getProperty("spring.datasource.url"),
                environment.getProperty("spring.datasource.username"),
                environment.getProperty("spring.datasource.password")
        );
    }

    @Override
    public void run(ApplicationArguments arguments) {
        try {
            initializeDatabase();
            log.info("데이터베이스 초기화가 완료되었습니다.");
        } catch (Exception e) {
            log.error("데이터베이스 초기화에 실패했습니다.", e);
            throw new IllegalStateException("데이터베이스 초기화에 실패했습니다.", e);
        }
    }

    private void initializeDatabase() throws IOException, SQLException {
        String script = loadScript();

        try (Connection connection = DriverManager.getConnection(
                DBConnection.getServerUrl(),
                DBConnection.getUser(),
                DBConnection.getPassword()
        ); Statement statement = connection.createStatement()) {

            for (String sql : splitStatements(script)) {
                statement.execute(sql);
            }
        }
    }

    private String loadScript() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream input = classLoader.getResourceAsStream(DATABASE_SCRIPT)) {
            if (input == null) {
                throw new IllegalStateException(DATABASE_SCRIPT + " 파일을 찾을 수 없습니다.");
            }

            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private String[] splitStatements(String script) {
        String withoutComments = script.replaceAll("(?m)^\\s*--.*$", "");

        return java.util.Arrays.stream(withoutComments.split(";"))
                .map(String::trim)
                .filter(sql -> !sql.isEmpty())
                .toArray(String[]::new);
    }
}
