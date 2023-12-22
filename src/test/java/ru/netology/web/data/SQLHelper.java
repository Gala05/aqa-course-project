package ru.netology.web.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/tour", "tourist", "pass");
    }

    public static String RowCount() throws SQLException {
        var code = "SELECT COUNT(*) FROM order_entity;";
        Long count  = null;
        try (var conn = getConn()) {
            count = runner.query(conn, code, new ScalarHandler<>());
        } catch (SQLException e) {
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
