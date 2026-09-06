package org.example.atumari.common.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MySQL 연결을 생성하는 공통 클래스입니다.
 *
 * <p>DAO에서는 아래처럼 finally에서 closeDB()를 호출하세요.</p>
 *
 * <pre>{@code
 * Connection con = null;
 * PreparedStatement ps = null;
 * ResultSet rs = null;
 *
 * try {
 *     con = DBConnection.getConnection();
 *     ps = con.prepareStatement(sql);
 *     rs = ps.executeQuery();
 *     // 조회 결과 처리
 * } catch (SQLException e) {
 *     e.printStackTrace();
 * } finally {
 *     DBConnection.closeDB(con, ps, rs);
 * }
 * }</pre>
 */
public final class DBConnection {

    private static String serverUrl;
    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            // Tomcat에서도 MySQL JDBC 드라이버가 확실하게 등록되도록 한 번 로딩합니다.
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("MySQL JDBC 드라이버를 찾을 수 없습니다.", e);
        }
    }

    private DBConnection() {
        // 객체를 만들지 않고 DBConnection.getConnection()으로 사용합니다.
    }

    /**
     * Spring Boot가 .env에서 읽어 전달한 접속 정보로 MySQL 연결을 생성합니다.
     * 사용이 끝난 Connection은 DAO의 finally에서 closeDB()로 닫아야 합니다.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                getRequiredValue(url, "DB_URL"),
                getRequiredValue(user, "DB_USERNAME"),
                getRequiredValue(password, "DB_PASSWORD")
        );
    }

    /**
     * Spring 설정의 데이터베이스 접속 정보를 기존 JDBC 코드에서 사용하도록 저장합니다.
     */
    public static void configure(
            String configuredServerUrl,
            String configuredUrl,
            String configuredUser,
            String configuredPassword
    ) {
        serverUrl = configuredServerUrl;
        url = configuredUrl;
        user = configuredUser;
        password = configuredPassword;
    }

    /**
     * ResultSet, PreparedStatement, Connection을 안전한 순서로 종료합니다.
     * 사용하지 않은 객체는 null로 전달해도 됩니다.
     */
    public static void closeDB(
            Connection connection,
            PreparedStatement statement,
            ResultSet resultSet
    ) {
        close(resultSet);
        close(statement);
        close(connection);
    }

    private static void close(AutoCloseable resource) {
        if (resource == null) {
            return;
        }

        try {
            resource.close();
        } catch (Exception e) {
            System.err.println("DB 자원 종료 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private static String getRequiredValue(String value, String key) {
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(key + " 데이터베이스 설정이 없습니다.");
        }

        return value;
    }

    static String getServerUrl() {
        return getRequiredValue(serverUrl, "DB_SERVER_URL");
    }

    static String getUser() {
        return getRequiredValue(user, "DB_USERNAME");
    }

    static String getPassword() {
        return getRequiredValue(password, "DB_PASSWORD");
    }
}
