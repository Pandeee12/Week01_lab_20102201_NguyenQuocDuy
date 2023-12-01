package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.connectDB.ConnectDB;
import vn.edu.iuh.fit.models.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogRepository {
    private final Connection connection;

    public LogRepository() {
        connection = ConnectDB.getInstance().getConnection();
    }

    public void logLogin(String accountId) {
        String sql = "INSERT INTO log (account_id, login_time) VALUES (?, NOW())";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, accountId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void logLogout(String accountId) {
        // Record log logout from CSDL
        String sql = "UPDATE log SET logout_time = NOW() WHERE account_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, accountId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Logs> getAllLogs() {
        List<Logs> logList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM log";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Logs log = new Logs();
                log.setLogId(resultSet.getInt("id"));
                log.setAccountId(resultSet.getString("account_id"));
                log.setLoginTime(resultSet.getTimestamp("login_time"));
                log.setLogoutTime(resultSet.getTimestamp("logout_time"));
                logList.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logList;
    }


}
