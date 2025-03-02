/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import model.userDAO;
import utils.utils;

/**
 *
 * @author Teacher
 */
public class LoginServlet extends HttpServlet {

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
        // 1. Check if session is exist or not and stop it creating new
        HttpSession session = request.getSession(false);

        // 2. If exist then take params username
        String username
                = (session == null)
                        ? null : (String) session.getAttribute("username");
        // 3. If usename not null then redirect to dashboard
        if (username != null) {
            response.sendRedirect("dashboard.html");
        } else {
            // 4. Else foward to login
            response.sendRedirect("login.jsp");
        }
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

        // 2. Check if params is empty then forward back
        if (utils.isEmptyInput(username)
                || utils.isEmptyInput(password)) {
            request.setAttribute("error", "Empty input");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // 3. Check login if true then redirected to dashboard
        userDAO uDAO = new userDAO();
        try {
            if (uDAO.checkLogin(username, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("dashboard.html");
            } else {        // 4. Else forward back invalid params
                request.setAttribute("error", "Invalid account");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getCause());
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
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
