/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author truon
 */
public class SendEmail {
     public static void sendConfirmationEmail(String recipientEmail, String Code) throws MessagingException {
        String senderEmail = "truongvxhe176609@fpt.edu.vn";
        String senderPassword = "yiev xvxw khsb sguk"; 

        // Set up email server properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create session with email account authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        // Create the email message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject("[CARDLORD] : Your account activation code");

        templateMail temp = new templateMail();
        
        message.setContent( temp.sentMail(Code), "text/html");

        // Send the email
        Transport.send(message);
    }
     
     
     
     
      public static void SentEmailForgotPass(String recipientEmail, String Code) throws MessagingException {
    String senderEmail = "truongvxhe176609@fpt.edu.vn";
    String senderPassword = "yiev xvxw khsb sguk"; 

    // Set up email server properties
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    // Create session with email account authentication
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, senderPassword);
        }
    });

    // Create the email message
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(senderEmail));
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
    message.setSubject("[CARDLORD] : Click to link to reset password");

    // Create the content of the email with HTML
   String tempEmail = 
    "<html>" +
    "<body style='font-family: Arial, sans-serif; background-color: #f7f7f7; padding: 20px;'>" +
    "<div style='max-width: 600px; margin: auto; background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);'>" +
    "<h2 style='color: #333;'>Password Reset Request</h2>" +
    "<p style='color: #555;'>Hi there,</p>" +
    "<p style='color: #555;'>We received a request to reset your password. Click the link below to reset it:</p>" +
    "<p style='text-align: center;'>" +
    "<a href='http://localhost:9999/PRJ_SE1938/resetpassword?code=" + Code + "&email=" + recipientEmail + "' " +
    "style='background-color: #007BFF; color: #fff; padding: 12px 24px; text-decoration: none; font-weight: bold; border-radius: 4px;'>Reset Your Password</a>" +
    "</p>" +
    "<p style='color: #555;'>If you didn't request a password reset, please ignore this email.</p>" +
    "<p style='color: #555;'>Thanks,</p>" +
    "<p style='color: #555;'>The CARDLORD Team</p>" +
    "</div>" +
    "</body>" +
    "</html>";

    message.setContent(tempEmail, "text/html");

    // Send the email
    Transport.send(message);
}

}
