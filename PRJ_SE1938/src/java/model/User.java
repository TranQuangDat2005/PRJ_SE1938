package model;

import java.util.Date;

public class User {

    private int userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean vipStatus; // VIP status (0 = No, 1 = Yes)
    private boolean role; // Role (0 = User, 1 = Admin)
    private Date dob; // Date of Birth
    private String verificationCode;
    private boolean isVerification; // Verification status (0 = No, 1 = Yes)

    // Constructors
    public User() {
    }

    public User(int userId, String username, String email, String phoneNumber, String password,
            boolean vipStatus, boolean role, Date dob, String verificationCode, boolean isVerification) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.vipStatus = vipStatus;
        this.role = role;
        this.dob = dob;
        this.verificationCode = verificationCode;
        this.isVerification = isVerification;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(boolean vipStatus) {
        this.vipStatus = vipStatus;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isIsVerification() {
        return isVerification;
    }

    public void setIsVerification(boolean isVerification) {
        this.isVerification = isVerification;
    }

}
