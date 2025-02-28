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

/**
 *
 * @author Phan Sơn
 */
public class userDAO {

    public boolean checkExist(String username){
        return true;
    }
    
    public boolean checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {

        String stm = "SELECT * FROM [user] WHERE [username]  ? AND [password] = ?";

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
    
    public boolean createUser(User newUser)
            throws SQLException, ClassNotFoundException{
        String stm = "INSERT INTO ";
        
        try (Connection conn = DBHelper.makeConnection();
                PreparedStatement ps = conn.prepareStatement(stm);){
            
        }
        return false;
    }
}
