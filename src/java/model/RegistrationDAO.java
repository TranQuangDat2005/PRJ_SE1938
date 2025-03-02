package model;

import dal.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RegistrationDAO implements Serializable {

    // Method to check duplicate entries
    public String checkDuplicate(String username, String email, String phone_number) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                // Check username
                String checkUserSql = "SELECT username FROM [user] WHERE username = ?";
                stm = con.prepareStatement(checkUserSql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return "Username already exists!";
                }
                rs.close();
                stm.close();

                // Check email
                String checkEmailSql = "SELECT email FROM [user] WHERE email = ?";
                stm = con.prepareStatement(checkEmailSql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return "Email already exists!";
                }
                rs.close();
                stm.close();

                // Check phone number
                String checkPhoneSql = "SELECT phone_number FROM [user] WHERE phone_number = ?";
                stm = con.prepareStatement(checkPhoneSql);
                stm.setString(1, phone_number);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return "Phone number already exists!";
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return null; // No duplicate found
    }

    public boolean Register(String username, String password, String email, String phone_number, Date dob)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String insertSql = "INSERT INTO [user] (username, email, phone_number, [password], dob) "
                        + "VALUES (?, ?, ?, ?, ?)";
                stm = con.prepareStatement(insertSql);
                stm.setString(1, username);
                stm.setString(2, email);
                stm.setString(3, phone_number);
                stm.setString(4, password);
                stm.setDate(5, new java.sql.Date(dob.getTime()));

                int row = stm.executeUpdate();
                return row > 0;
            }
        } finally {
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        return false;
    }
}
