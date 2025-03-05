package utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    public static void sendConfirmationEmail(String email, String code) {

        String toEmail = email;
        String fromEmail = "truongvxhe176609@fpt.edu.vn";
        String password = "yiev xvxw khsb sguk";
        try {
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.gmail.com"); // Sử dụng Gmail SMTP server
//            pr.setProperty("mail.smtp.port", "587"); // Cổng cho STARTTLS

            pr.setProperty("mail.smtp.port", "465");  // Dùng cổng 465 cho SSL

            pr.setProperty("mail.smtp.auth", "true");

            pr.put("mail.smtp.ssl.enable", "true");  // Kích hoạt SSL

            pr.setProperty("mail.smtp.starttls.enable", "true");

            // Sử dụng TLS cho kết nối
            pr.put("mail.smtp.socketFactory.port", "587");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            pr.put("mail.smtp.ssl.trust", "*"); // Cho phép tất cả các chứng chỉ SSL

            // Get session
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            // Create email message
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail)); // Set sender's email
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail)); // Set recipient's email
            mess.setSubject("[CARDLORD] : Your account activation code");

            templateMail temp = new templateMail();
//            mess.setText("Registered successfully. Please verify your account using this link."); // Body of email
            mess.setContent(temp.sentMail(code), "text/html");

            // Send email
            Transport.send(mess);
        } catch (Exception e) {
            e.printStackTrace(); // Print any errors to the console
        }

    }

    public static void SentEmailForgotPass(String email, String code) {
        boolean test = false;

        String toEmail = email;
        String fromEmail = "truongvxhe176609@fpt.edu.vn";
        String password = "yiev xvxw khsb sguk";
        try {
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.gmail.com"); // Sử dụng Gmail SMTP server
//            pr.setProperty("mail.smtp.port", "587"); // Cổng cho STARTTLS

            pr.setProperty("mail.smtp.port", "465");  // Dùng cổng 465 cho SSL

            pr.setProperty("mail.smtp.auth", "true");

            pr.put("mail.smtp.ssl.enable", "true");  // Kích hoạt SSL

            pr.setProperty("mail.smtp.starttls.enable", "true");

            // Sử dụng TLS cho kết nối
            pr.put("mail.smtp.socketFactory.port", "587");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            pr.put("mail.smtp.ssl.trust", "*"); // Cho phép tất cả các chứng chỉ SSL

            // Get session
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            // Create email message
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail)); // Set sender's email
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail)); // Set recipient's email
            mess.setSubject("[CARDLORD] : Click to link to reset password");

            // Create the content of the email with HTML
            String tempEmail
                    = "<html>"
                    + "<body style='font-family: Arial, sans-serif; background-color: #f7f7f7; padding: 20px;'>"
                    + "<div style='max-width: 600px; margin: auto; background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);'>"
                    + "<h2 style='color: #333;'>Password Reset Request</h2>"
                    + "<p style='color: #555;'>Hi there,</p>"
                    + "<p style='color: #555;'>We received a request to reset your password. Click the link below to reset it:</p>"
                    + "<p style='text-align: center;'>"
                    + "<a href='http://localhost:9999/PRJ_SE1938/resetpassword?code=" + code + "&email=" + email + "' "
                    + "style='background-color: #007BFF; color: #fff; padding: 12px 24px; text-decoration: none; font-weight: bold; border-radius: 4px;'>Reset Your Password</a>"
                    + "</p>"
                    + "<p style='color: #555;'>If you didn't request a password reset, please ignore this email.</p>"
                    + "<p style='color: #555;'>Thanks,</p>"
                    + "<p style='color: #555;'>The CARDLORD Team</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            mess.setContent(tempEmail, "text/html");
            // Send email
            Transport.send(mess);
            test = true; // Set test to true when the email is sent successfully
        } catch (Exception e) {
            e.printStackTrace(); // Print any errors to the console
        }

    }

}
