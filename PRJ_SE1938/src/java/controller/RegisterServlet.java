package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import model.User;
import model.userDAO;
import utils.utils;

public class RegisterServlet extends HttpServlet {


    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get params
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone_number");
        String dobStr = request.getParameter("dob");

        // 2. If params empty, forward Error.
        if (utils.isEmptyInput(username)
                || utils.isEmptyInput(password)
                || utils.isEmptyInput(email)
                || utils.isEmptyInput(phone)
                || utils.isEmptyInput(dobStr)) {
            request.setAttribute("error", "Empty input");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // 3. Create new object 
        Date dob = Date.valueOf(dobStr);
        User newUser = new User(username, email, phone, password, dob);

        // 4. Add to Database
        userDAO uDao = new userDAO();
        try {
            uDao.createUser(newUser);
            // 5. If success added, redirect to Login
            response.sendRedirect("login.jsp");

        } catch (SQLException e) {
            // 6. If Exception is added more than 1 then forward back error
            request.setAttribute("error", e.getCause());
            request.getRequestDispatcher("register.html").forward(request, response);

        } catch (ClassNotFoundException e) {
            request.setAttribute("error", "Can't connect to database");
            request.getRequestDispatcher("register.html").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Server is dead");
            request.getRequestDispatcher("register.html").forward(request, response);

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
