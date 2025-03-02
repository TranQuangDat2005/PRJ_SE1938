/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Phan Sơn
 */
public class User {

    private String username; // Unique
    private String email;// Unique
    private String phone_number;// Unique
    private String password;
    private Integer vip_status;
    private Integer role;
    private Date dob;

    public User() {
    }

    public User(String username, String email, String phone_number, String password, Date dob) {
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.dob = dob;
    }

    public User(String username, String email, String phone_number, String password, int vip_status, int role, Date dob) {
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.vip_status = vip_status;
        this.role = role;
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public String getPassword() {
        return password;
    }

    public Integer getVip_status() {
        return vip_status;
    }

    public Integer getRole() {
        return role;
    }

    public Date getDob() {
        return dob;
    }

}
