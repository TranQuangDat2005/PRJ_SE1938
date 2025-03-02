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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Date;
import model.User;
import model.userDAO;
import utils.utils;

public class RegisterServlet extends HttpServlet {

    private final String INVALID_PAGE = "register.html";
    private final String LOGIN_PAGE = "login.jsp";

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        String url = INVALID_PAGE;
//
//        // Lấy giá trị của btAction
//        String button = request.getParameter("btAction");
//        try {
//            // Kiểm tra nếu người dùng nhấn nút Register
//            if (button != null && button.equals("Register")) {
//                String username = request.getParameter("username");
//                String password = request.getParameter("password");
//                String email = request.getParameter("email");
//                String phone_number = request.getParameter("phone_number");
//                String dob = request.getParameter("dob");
//
//                // Chuyển đổi DoB từ String sang Date
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                Date dobInput = dateFormat.parse(dob);
//
//                // Kết nối csdl và thực hiện insert
//                RegistrationDAO dao = new RegistrationDAO();
//                boolean result = dao.Register(username, password, email, phone_number, dobInput);
//                if (result) {
//                    url = LOGIN_PAGE;
//                }
//            }
//        } catch (SQLException | ClassNotFoundException | ParseException ex) {
//            ex.printStackTrace();
//        } finally {
//            response.sendRedirect(url);
//            out.close();
//        }
//    }
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
            request.getRequestDispatcher("register.html").forward(request, response);
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
