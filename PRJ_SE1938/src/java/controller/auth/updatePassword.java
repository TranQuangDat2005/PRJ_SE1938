/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import model.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Helper;

/**
 *
 * @author truon
 */
public class updatePassword extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updatePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updatePassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private final String INVALID_PAGE = "resetPassword.jsp"; // Page to show errors
    private final String LOGIN_PAGE = "login.jsp"; // Redirect on success

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = request.getParameter("newPassword");
        String email = request.getParameter("email");

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

        AccountDAO dao = new AccountDAO();

        try {
            boolean check = dao.updatePasswordByEmail(email, password);
            if (check == true) {
                request.setAttribute("errorMessage", "Update Password Successfully.");
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                return;
            } else {

                request.setAttribute("errorMessage", "Update Password fail.");
                request.getRequestDispatcher(INVALID_PAGE).forward(request, response);
                return;
            }

        } catch (SQLException ex) {
            Logger.getLogger(updatePassword.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(updatePassword.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
