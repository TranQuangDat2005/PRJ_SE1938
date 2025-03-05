package model;

import dal.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import model.User;
import utils.Helper;
import utils.SendEmail;

public class AccountDAO implements Serializable {

    // Login
    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT username FROM [user] WHERE [username] = ? AND [password] = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean register(String username, String email, String phoneNumber, String password, String verifyCode) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                // Check if username, email or phone already exists in the database
                String checkSQL = "SELECT username, email, phone_number FROM [user] WHERE username = ? OR email = ? OR phone_number = ?";
                stm = con.prepareStatement(checkSQL);
                stm.setString(1, username);
                stm.setString(2, email);
                stm.setString(3, phoneNumber);
                rs = stm.executeQuery();
                if (rs.next()) {
                    // If any of the fields (username, email, or phone_number) is already registered, return false
                    return false;
                }

                // If not, proceed with inserting the new user
                String insertSQL = "INSERT INTO [user] (username, email, phone_number, [password], verificationCode) VALUES (?, ?, ?, ?, ?)";
                stm = con.prepareStatement(insertSQL);
                stm.setString(1, username);
                stm.setString(2, email);
                stm.setString(3, phoneNumber);
                stm.setString(4, password);
                stm.setString(5, verifyCode);
                int rowsAffected = stm.executeUpdate();
                return rowsAffected > 0; // Return true if the insertion was successful
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    // Forgot Password (Generate a verification code and send it to the email)
    public boolean forgotPassword(String email) throws SQLException, ClassNotFoundException, MessagingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                // Check if email exists in the database
                String checkEmailSQL = "SELECT email FROM [user] WHERE email = ?";
                stm = con.prepareStatement(checkEmailSQL);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    // Email exists, generate a random verification code
                    String Verifycode = Helper.generateCode(6);

                    // Update the user record with the generated verification code
                    String updateSQL = "UPDATE [user] SET verificationCode = ? WHERE email = ?";
                    stm = con.prepareStatement(updateSQL);
                    stm.setString(1, Verifycode);
                    stm.setString(2, email);
                    int rowsAffected = stm.executeUpdate();
                    if (rowsAffected > 0) {
                        // Send the verification code to the user's email (simulated here, implement email sending logic as needed)
                        SendEmail.SentEmailForgotPass(email, Verifycode);

                        return true;
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    // Helper method to generate a random verification code
    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(999999);
        return String.format("%06d", code); // Return a 6-digit string
    }

    // Helper method to simulate sending the verification code to the user's email
    private void sendVerificationCodeToEmail(String email, String verificationCode) {
        // You would implement your email sending logic here.
        // For now, we just simulate the action by printing the verification code.
        System.out.println("Verification code sent to " + email + ": " + verificationCode);
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                // Kiểm tra xem người dùng có tồn tại và mật khẩu cũ có đúng không
                String checkSQL = "SELECT password FROM [user] WHERE username = ? AND password = ?";
                stm = con.prepareStatement(checkSQL);
                stm.setString(1, username);
                stm.setString(2, oldPassword);
                rs = stm.executeQuery();

                if (rs.next()) {
                    // Nếu mật khẩu cũ đúng, tiến hành thay đổi mật khẩu mới
                    String updateSQL = "UPDATE [user] SET password = ? WHERE username = ?";
                    stm = con.prepareStatement(updateSQL);
                    stm.setString(1, newPassword);
                    stm.setString(2, username);
                    int rowsAffected = stm.executeUpdate();
                    return rowsAffected > 0; // Nếu có ít nhất 1 dòng bị ảnh hưởng, tức là cập nhật thành công
                } else {
                    // Mật khẩu cũ không đúng
                    return false;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updatePasswordByUsername(String username, String newPassword) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                // Truy vấn SQL để cập nhật mật khẩu mới cho người dùng theo username
                String updateSQL = "UPDATE [user] SET password = ? WHERE username = ?";
                stm = con.prepareStatement(updateSQL);
                stm.setString(1, newPassword);
                stm.setString(2, username);
                int rowsAffected = stm.executeUpdate();
                return rowsAffected > 0; // Nếu có ít nhất 1 dòng bị ảnh hưởng, tức là cập nhật thành công
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false; // Trả về false nếu không có dòng nào bị ảnh hưởng (nghĩa là người dùng không tồn tại)
    }

    public boolean updatePasswordByEmail(String email, String newPassword) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                // Truy vấn SQL để cập nhật mật khẩu mới cho người dùng theo username
                String updateSQL = "UPDATE [user] SET password = ? WHERE email = ?";
                stm = con.prepareStatement(updateSQL);
                stm.setString(1, newPassword);
                stm.setString(2, email);
                int rowsAffected = stm.executeUpdate();
                return rowsAffected > 0; // Nếu có ít nhất 1 dòng bị ảnh hưởng, tức là cập nhật thành công
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false; // Trả về false nếu không có dòng nào bị ảnh hưởng (nghĩa là người dùng không tồn tại)
    }

    public boolean verifyCode(String email, String inputCode) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                // Kiểm tra mã xác minh và email cùng lúc
                String updateSQL = "UPDATE [user] SET verificationCode = NULL, isVerification = TRUE "
                        + "WHERE email = ? AND verificationCode = ?";
                stm = con.prepareStatement(updateSQL);
                stm.setString(1, email);
                stm.setString(2, inputCode);

                int rowsAffected = stm.executeUpdate();

                // Nếu có bản ghi bị ảnh hưởng, có nghĩa là mã xác minh đúng và đã được cập nhật
                return rowsAffected > 0;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false; // Trả về false nếu không có bản ghi nào bị ảnh hưởng
    }

    public boolean setVerificationCode(String username, String verificationCode) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                // Kiểm tra người dùng qua username và cập nhật verificationCode
                String updateSQL = "UPDATE [user] SET verificationCode = ? WHERE username = ?";
                stm = con.prepareStatement(updateSQL);
                stm.setString(1, verificationCode);
                stm.setString(2, username);

                int rowsAffected = stm.executeUpdate();
                return rowsAffected > 0; // Nếu có bản ghi bị ảnh hưởng, tức là cập nhật thành công
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false; // Trả về false nếu không có bản ghi nào bị ảnh hưởng
    }

    public User getUserByUsername(String username) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        User user = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM [user] WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();

                if (rs.next()) {
                    user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setPassword(rs.getString("password"));
                    user.setVipStatus(rs.getBoolean("vip_status"));
                    user.setRole(rs.getBoolean("role"));
                    user.setDob(rs.getDate("dob"));
                    user.setVerificationCode(rs.getString("verificationCode"));
                    user.setIsVerification(rs.getBoolean("isVerification"));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return user;
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        try {
            System.out.println(dao.forgotPassword("dg.hungdung@gmail.com"));
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
