package controller;

import model.RegistrationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterServlet extends HttpServlet {

    private final String INVALID_PAGE = "register.jsp"; // Page to show errors
    private final String LOGIN_PAGE = "login.jsp"; // Redirect on success

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                String dob = request.getParameter("dob");

                // Convert DoB from String to Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dobInput = dateFormat.parse(dob);

                // Call DAO to check & register user
                RegistrationDAO dao = new RegistrationDAO();
                String errorMessage = dao.checkDuplicate(username, email, phone_number);

                if (errorMessage != null) {
                    // If duplicate error found, show message
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher(INVALID_PAGE).forward(request, response);
                    return;
                }

                // Register new user
                boolean result = dao.Register(username, password, email, phone_number, dobInput);
                if (result) {
                    url = LOGIN_PAGE;
                    response.sendRedirect(url);
                } else {
                    request.setAttribute("errorMessage", "Registration failed. Please try again!");
                    request.getRequestDispatcher(INVALID_PAGE).forward(request, response);
                }
            }
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Server error: " + ex.getMessage());
            request.getRequestDispatcher(INVALID_PAGE).forward(request, response);
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
}
