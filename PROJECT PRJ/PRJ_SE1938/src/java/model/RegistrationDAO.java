/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dal.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Teacher
 */
public class RegistrationDAO implements Serializable {

    public boolean Register(String username, String password, String email, String phone_number, Date dob)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            //Kiem tra xem ket noi csdl
            if (con != null) {
                String checkUserNameSql = "SELECT username "
                        + "FROM [user] "
                        + "WHERE [username] = ?";
                stm = con.prepareStatement(checkUserNameSql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                //Kiem tra email da ton tai neu da ton tai tra ve false
                if (rs.next()) {
                    con.rollback();
                    return false;
                }

                String checkEmailSql = "SELECT email "
                        + "FROM [user] "
                        + "WHERE email = ?";
                stm = con.prepareStatement(checkEmailSql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                //Kiem tra email da ton tai neu da ton tai tra ve false
                if (rs.next()) {
                    con.rollback();
                    return false;
                }

                String checkPhoneNumberSql = "SELECT phone_number "
                        + "FROM [user] "
                        + "WHERE phone_number = ?";
                stm = con.prepareStatement(checkPhoneNumberSql);
                stm.setString(1, phone_number);
                rs = stm.executeQuery();
                //Kiem tra sdt da ton tai neu da ton tai tra ve false
                if (rs.next()) {
                    con.rollback();
                    return false;
                }

                String insertSql = "INSERT INTO [user] (username, email, phone_number, [password], dob) "
                        + "VALUES (?, ?, ?, ?, ?)";
                stm = con.prepareStatement(insertSql);
                stm.setString(1, username);
                stm.setString(2, email);
                stm.setString(3, phone_number);
                stm.setString(4, password);
                stm.setDate(5, new java.sql.Date(dob.getTime()));

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                } else {
                    con.rollback();
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
}
