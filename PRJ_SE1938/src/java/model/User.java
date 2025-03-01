/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Phan Sơn
 */
public class User {

    private String username; // Unique
    private String email;// Unique
    private String phoneNumber;// Unique
    private String password;
    private boolean vipStatus;
    private boolean role;

    public User() {
    }

    public User(String username, String email, String phoneNumber, String password, boolean vipStatus, boolean role) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.vipStatus = vipStatus;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVipStatus() {
        return vipStatus;
    }

    public boolean isRole() {
        return role;
    }

}
