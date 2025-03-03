package controller.auth;

import dal.AccountDAO;
import dal.RegistrationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utils.Helper;
import utils.SendEmail;
import utils.templateMail;

public class RegisterServlet extends HttpServlet {

    private final String INVALID_PAGE = "register.jsp"; // Page to show errors
    private final String VERIFY_PAGE = "verify.jsp"; // Redirect on success

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");

        String url = INVALID_PAGE;

        // Get the action button value
        String button = request.getParameter("btAction");
        try {
            if (button != null && button.equals("This is me")) {
                // Get user input from form
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String phone_number = request.getParameter("phone_number");
                //  String dob = request.getParameter("dob");

                // Kiểm tra email và phone trước khi tiếp tục đăng ký
                if (!Helper.validateEmail(email)) {
                    request.setAttribute("errorMessage", "Invalid email format. Please enter a valid email.");
                    request.getRequestDispatcher(INVALID_PAGE).forward(request, response);
                    return;
                }

                if (!Helper.validatePhone(phone_number)) {
                    request.setAttribute("errorMessage", "Invalid phone number format. Please enter a valid phone number.");
                    request.getRequestDispatcher(INVALID_PAGE).forward(request, response);
                    return;
                }

                 if (!Helper.isValidPassword(password)) {
                    request.setAttribute("errorMessage", "Invalid Password. Please enter number and character and length >5.");
                    request.getRequestDispatcher(INVALID_PAGE).forward(request, response);
                    return;
                }

                
                // Get CAPTCHA input
                String captcha = request.getParameter("captcha");

                // Get the CAPTCHA value stored in session
                String captcha_txt = (String) request.getSession().getAttribute("captcha");

                // Validate CAPTCHA
                if (captcha_txt == null || !captcha_txt.equals(captcha)) {
                    request.setAttribute("errorMessage", "Invalid CAPTCHA. Please try again.");
                    request.getRequestDispatcher(INVALID_PAGE).forward(request, response);
                    return;
                }

                String Verifycode = Helper.generateCode(6);
                // Convert DoB from String to Date
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                Date dobInput = dateFormat.parse(dob);

                AccountDAO dao = new AccountDAO();

                
                
                // Register new user
                boolean result = dao.register(username, email, phone_number, password, Verifycode);
                if (result) {
                    // Send confirmation email
                    SendEmail.sendConfirmationEmail(email, Verifycode);
                     request.setAttribute("email",email );

                    url = VERIFY_PAGE  ;
                    request.getRequestDispatcher(url).forward(request, response);

                } else {
                    request.setAttribute("errorMessage", "Registration failed.May Be Duplicate username, email , phone Please try again!");
                    request.getRequestDispatcher(INVALID_PAGE).forward(request, response);
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Server error: " + ex.getMessage());
            request.getRequestDispatcher(INVALID_PAGE).forward(request, response);
        }
    }

    
    
    
    
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
