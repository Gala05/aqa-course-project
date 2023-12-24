package ru.netology.web.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private static Properties properties =  null;
    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("application.properties"));
            String url = properties.getProperty("spring.datasource.url");
            String user =  properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException, IOException {
        return DriverManager.getConnection(properties.getProperty("spring.datasource.url"), properties.getProperty("spring.datasource.username"), properties.getProperty("spring.datasource.password"));
    }

    public static String RowCount() throws SQLException {
        var code = "SELECT COUNT(*) FROM order_entity;";
        Long count  = null;
        try (var conn = getConn()) {
            count = runner.query(conn, code, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(count);
    }

    public static String approvedRowCountPaymentCard() throws SQLException {
        var code = "SELECT COUNT(*) FROM payment_entity WHERE status='APPROVED';";
        Long count  = null;
        try (var conn = getConn()) {
            count = runner.query(conn, code, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(count);
    }

    public static String declineRowCountPaymentCard() throws SQLException {
        var code = "SELECT COUNT(*) FROM payment_entity WHERE status='DECLINED';";
        Long count  = null;
        try (var conn = getConn()) {
            count = runner.query(conn, code, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(count);
    }

    public static String approvedRowCountCreditCard() throws SQLException {
        var code = "SELECT COUNT(*) FROM credit_request_entity WHERE status='APPROVED';";
        Long count  = null;
        try (var conn = getConn()) {
            count = runner.query(conn, code, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(count);
    }

    public static String declineRowCountCreditCard() throws SQLException {
        var code = "SELECT COUNT(*) FROM credit_request_entity WHERE status='DECLINED';";
        Long count  = null;
        try (var conn = getConn()) {
            count = runner.query(conn, code, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(count);
    }

    @SneakyThrows
    public static void cleanDataBase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM payment_entity");
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
    }
}
