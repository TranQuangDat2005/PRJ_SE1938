/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Phan Sơn
 */
public class userDAO {

    private boolean isDuplicateInfo(Connection conn, String username,
            String email, String phoneNumber) throws SQLException {
        String stm = "SELECT COUNT(*) FROM [user] "
                + "WHERE [username]  ? "
                + "OR [password] = ? "
                + "OR email = ?";
        try (PreparedStatement ps = conn.prepareStatement(stm)) {
            ResultSet rs = ps.executeQuery();

            // If there is a result and at least there is one duplicate value
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {

        String stm = "SELECT * FROM [user] "
                + "WHERE [username]  ? "
                + "AND [password] = ?";

        // 1. Open connection and Create Prepared Statement
        try (Connection conn = DBHelper.makeConnection(); PreparedStatement ps = conn.prepareStatement(stm);) {

            // 2. Setup values in statement
            ps.setString(1, username);
            ps.setString(2, password);

            // 3. Get result from execute statement
            ResultSet rs = ps.executeQuery();
            // 4. If result have next then return True
            if (rs.next()) {
                return true;
            } // 5. Else return false
            else {
                return false;
            }
        }
    }

    /**
     * Create new user by both USER/ADMIN.
     *
     * @param newUser
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public void createUser(User newUser)
            throws SQLException, ClassNotFoundException, Exception {
        String stm = "INSERT INTO [user] "
                + "([username], email, phone_number, [password], dob) VALUES "
                + "(?,?,?,?,?)";

        try (Connection conn = DBHelper.makeConnection(); PreparedStatement ps = conn.prepareStatement(stm);) {
            if (this.isDuplicateInfo(conn,
                    newUser.getUsername(),
                    newUser.getEmail(),
                    newUser.getPhoneNumber())) {
                throw new SQLException("Error. Duplicate Information");
            }

            conn.setAutoCommit(false);

            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getEmail());
            ps.setString(3, newUser.getPhoneNumber());
            ps.setString(4, newUser.getPassword());
            ps.setDate(5, newUser.getDob());

            int insertedRows = ps.executeUpdate();

            if (insertedRows != 1) {
                conn.rollback();
                throw new SQLException("Error. More than 1 rows inserted. Rollback");
            } else {
                conn.commit();
            }
        }
    }

    /**
     * Get list of all user by ADMIN.
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<User> getAllUsers()
            throws SQLException, ClassNotFoundException {
        String stm = "SELECT * FROM [user]";

        ArrayList<User> result = new ArrayList<>();

        try (Connection conn = DBHelper.makeConnection(); PreparedStatement ps = conn.prepareStatement(stm);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String phone_number = rs.getString("phone_number");
                String password = rs.getString("password");
                int vip_status = rs.getInt("vip_status");
                int role = rs.getInt("role");
                Date dob = rs.getDate("dob");

                result.add(new User(username, email, phone_number, password, vip_status, role, dob));
            }
        }
        return result;
    }

    /**
     * Update User's information by USER/ADMIN.
     *
     * @param user
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void updateUser(User user)
            throws SQLException, ClassNotFoundException {
        StringBuilder stm = new StringBuilder("UPDATE Products SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (user.getUsername() != null) {
            stm.append("username = ?, ");
            params.add(user.getUsername());
        }
        if (user.getEmail() != null) {
            stm.append("email = ?, ");
            params.add(user.getEmail());
        }
        if (user.getPhoneNumber() != null) {
            stm.append("phone_number = ?, ");
            params.add(user.getPhoneNumber());
        }
        if (user.getPassword() != null) {
            stm.append("password = ?, ");
            params.add(user.getPassword());
        }
        if (user.getVip_status() != null) {
            stm.append("vip_status = ?, ");
            params.add(user.getVip_status());
        }
        if (user.getRole() != null) {
            stm.append("role = ?, ");
            params.add(user.getRole());
        }
        if (user.getDob() != null) {
            stm.append("dob = ?, ");
            params.add(user.getDob());
        }

        try (Connection conn = DBHelper.makeConnection(); PreparedStatement ps = conn.prepareStatement(stm.toString())) {
            conn.setAutoCommit(false);

            for (int i = 0; i < params.size(); i++) {
                Object tmp = params.get(i);
                if (tmp instanceof String) {
                    ps.setString(i + 1, (String) tmp);
                } else if (tmp instanceof Integer) {
                    ps.setInt(i + 1, (Integer) tmp);
                } else if (tmp instanceof Date) {
                    ps.setDate(i + 1, (Date) tmp);
                }
            }
            
            int updatedRow = ps.executeUpdate();
            
            if (updatedRow != 1){
                conn.rollback();
                throw new SQLException("Error. Updated more than 1. Rollback");
            } else {
                conn.commit();
            }
        }

    }

    /**
     * Delete an User by ADMIN.
     *
     * @param email
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteUser(String email)
            throws SQLException, ClassNotFoundException {
        String stm = "DELETE FROM [user]"
                + " WHERE email = ?";
        try (Connection conn = DBHelper.makeConnection(); PreparedStatement ps = conn.prepareStatement(stm)) {
            ps.setString(1, email);

            conn.setAutoCommit(false);

            int deletedRows = ps.executeUpdate();
            if (deletedRows != 1) {
                conn.rollback();
                throw new SQLException("Error. More than 1 rows deleted. Rollback");
            } else {
                conn.commit();
            }

        }

    }
}
