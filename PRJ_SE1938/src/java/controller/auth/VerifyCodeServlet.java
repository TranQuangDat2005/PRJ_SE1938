package controller.auth;

import dal.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class VerifyCodeServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // You can render a page or message if needed. This is optional.
        response.sendRedirect("verify.jsp"); // Redirect to the verification page
    }

    /**
     * Handles the HTTP POST method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String inputCode = request.getParameter("verificationCode");

        AccountDAO dao = new AccountDAO();
        try {
            // Verify the code and update the user's verification status
            boolean isVerified = dao.verifyCode(email, inputCode);

            if (isVerified) {
                // If verification is successful, redirect to a success page
                response.sendRedirect("verification_success.jsp");
            } else {
                // If verification fails, show error message
                request.setAttribute("errorMessage", "Invalid verification code. Please try again.");
                request.getRequestDispatcher("verify.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            // Handle exceptions (database connection, etc.)
            ex.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while verifying the code. Please try again.");
            request.getRequestDispatcher("verify.jsp").forward(request, response);
        }
    }
}
