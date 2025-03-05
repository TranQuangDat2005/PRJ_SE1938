package controller.servletLogic;

import model.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import model.User;
import utils.Helper;
import utils.SendEmail;

public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "login.jsp";
    private final String SEARCH_PAGE = "dashboard.jsp";
    private final String VERIFY_PAGE = "verify.jsp"; // Trang xác minh mã

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = INVALID_PAGE;

        String button = request.getParameter("btAction");
        try {
            if (button.equals("Log in")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

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

                // Kết nối với cơ sở dữ liệu và kiểm tra thông tin đăng nhập
                AccountDAO dao = new AccountDAO();
                boolean result = dao.checkLogin(username, password);

                if (result) {

                    // Kiểm tra trạng thái xác minh của tài khoản
                    User user = dao.getUserByUsername(username); // Giả sử bạn có phương thức này trong DAO để lấy email

                    if (user.isIsVerification()) {
                        // Nếu tài khoản đã được xác minh, chuyển hướng đến trang dashboard
//                        url = SEARCH_PAGE;
                        request.getRequestDispatcher("dashboard.jsp").forward(request, response);

                    } else {
                        // Nếu tài khoản chưa được xác minh, chuyển hướng đến trang xác minh mã
                        url = VERIFY_PAGE;
                        // Truyền email vào trang xác minh
                        request.setAttribute("email", user.getEmail());

                        String Verifycode = Helper.generateCode(6);
                        dao.setVerificationCode(username, Verifycode);
                        SendEmail.sendConfirmationEmail(user.getEmail(), Verifycode);
                        request.getRequestDispatcher(url).forward(request, response);

                        return;
                    }
                } else {
                    // Nếu đăng nhập thất bại
                    request.setAttribute("errorMessage", "Sai tên đăng nhập hoặc mật khẩu!");
                    request.getRequestDispatcher(INVALID_PAGE).forward(request, response);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
