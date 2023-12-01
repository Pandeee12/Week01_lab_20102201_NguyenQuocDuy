package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.connectDB.ConnectDB;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.GrantAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrantAccessRepository {
    private final Connection connection;

    public GrantAccessRepository() {
        connection = ConnectDB.getInstance().getConnection();
    }

    public void add(GrantAccess access) {

        String sql = "INSERT INTO grant_access (account_id, role_id, is_grant, note) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, access.getAccountId());
            preparedStatement.setString(2, access.getRoleId());
            preparedStatement.setInt(3, access.getIsGrant());
            preparedStatement.setString(4, access.getNote());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GrantAccess> findByAccount(String accountId) {
        List<GrantAccess> accesses = new ArrayList<>();
        String sql = "SELECT * FROM grant_access WHERE account_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GrantAccess access = mapResultSetToAccess(resultSet);
                accesses.add(access);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accesses;
    }

    public boolean updateOrInsertGrantAccess(String accountId, String roleId) {
        // Check if the record already exists
        String selectSql = "SELECT * FROM grant_access WHERE account_id = ?";
        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
            selectStatement.setString(1, accountId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // Record already exists, update it
                String updateSql = "UPDATE grant_access SET role_id = ? WHERE account_id = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setString(1, roleId);
                    updateStatement.setString(2, accountId);
                    int rowsUpdated = updateStatement.executeUpdate();
                    return rowsUpdated > 0; // Return true if the update was successful
                }
            } else {
                // Record doesn't exist, insert a new one
                String insertSql = "INSERT INTO grant_access (account_id, role_id) VALUES (?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                    insertStatement.setString(1, accountId);
                    insertStatement.setString(2, roleId);
                    int rowsInserted = insertStatement.executeUpdate();
                    return rowsInserted > 0; // Return true if the insert was successful
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurred
        }
    }


    public List<GrantAccess> getAllGrant() {
        List<GrantAccess> grantAccesses = new ArrayList<>();
        try {
            String sql = "SELECT * FROM grant_access";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                GrantAccess grant = new GrantAccess();
                grant.setAccountId(rs.getString("account_id"));
                grant.setRoleId(rs.getString("role_id"));
                grant.setIsGrant(rs.getInt("is_grant"));
                grant.setNote(rs.getString("note"));
                grantAccesses.add(grant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grantAccesses;
    }

    public void update(GrantAccess access) {
        // Update access in the CSDL
        String sql = "UPDATE grant_access SET role_id = ?, is_grant = ?, WHERE account_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, access.getRoleId());
            preparedStatement.setInt(2, access.getIsGrant());
            preparedStatement.setString(3, access.getAccountId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String accountId, String roleId) {
        // Delete access from the CSDL
        String sql = "DELETE FROM grant_access WHERE account_id = ? AND role_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, accountId);
            preparedStatement.setString(2, roleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private GrantAccess mapResultSetToAccess(ResultSet resultSet) throws SQLException {
        String accountId = resultSet.getString("account_id");
        String roleId = resultSet.getString("role_id");
        Integer isGrant = resultSet.getInt("is_grant");
        String note = resultSet.getString("note");
        return new GrantAccess(accountId, roleId, isGrant, note);
    }
}
