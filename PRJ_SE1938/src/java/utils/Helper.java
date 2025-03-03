/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author truon
 */
public class Helper {
     public static String generateCode(int lengthCode) {
        String captcha = "1234567890";

        StringBuffer captchaBuffer = new StringBuffer();
        Random random = new Random();

        while (captchaBuffer.length() < lengthCode) {
            int index = (int) (random.nextFloat() * captcha.length());
            captchaBuffer.append(captcha.substring(index, index + 1));
        }
        return captchaBuffer.toString();
    }
     
     
     public static boolean validatePhone(String phoneNumber) {
    // Biểu thức chính quy để kiểm tra số điện thoại Việt Nam
    // Số điện thoại Việt Nam bắt đầu bằng 0, theo sau là 9 chữ số
    String phoneRegex = "^0(3|5|7|8|9)[0-9]{8}$";

    // Kiểm tra số điện thoại với biểu thức chính quy
    Pattern pattern = Pattern.compile(phoneRegex);
    Matcher matcher = pattern.matcher(phoneNumber);
    return matcher.matches();
}
     
    
public static boolean validateEmail(String email) {
    // Biểu thức chính quy để kiểm tra định dạng email
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    // Kiểm tra email với biểu thức chính quy
    Pattern pattern = Pattern.compile(emailRegex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
}

 public static boolean isValidPassword(String password) {
        // Biểu thức chính quy: ít nhất 5 ký tự, ít nhất 1 chữ cái và ít nhất 1 số
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$";

        return password.matches(regex);
    }

 
    
}
