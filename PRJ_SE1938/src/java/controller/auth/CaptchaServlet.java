package controller.auth;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;
import utils.Helper;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    private static final int WIDTH = 200; // Chiều rộng của ảnh
    private static final int HEIGHT = 50; // Chiều cao của ảnh

    /**
     * Xử lý yêu cầu GET và trả về ảnh CAPTCHA
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set content type to image
        response.setContentType("image/jpeg");

        // Tạo mã CAPTCHA 6 ký tự
        String captchaCode = Helper.generateCode(6);

        // Lưu mã CAPTCHA vào session để kiểm tra khi người dùng nhập vào form
        request.getSession().setAttribute("captcha", captchaCode);

        // Tạo hình ảnh CAPTCHA
        BufferedImage captchaImage = generateCaptchaImage(captchaCode);

        // Gửi hình ảnh về trình duyệt
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(captchaImage, "JPEG", out);
            out.flush();
        }
    }

    /**
     * Tạo hình ảnh CAPTCHA từ mã CAPTCHA
     */
    private BufferedImage generateCaptchaImage(String captchaCode) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics2D g = image.createGraphics();

        // Vẽ nền trắng
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Chọn kiểu chữ và màu sắc cho mã CAPTCHA
        g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 40));
        g.setColor(new java.awt.Color(0, 0, 0)); // Màu đen cho chữ

        // Vẽ mã CAPTCHA lên hình ảnh
        g.drawString(captchaCode, 40, 40);

        // Thêm nhiễu (noise) để làm khó khăn cho việc nhận diện tự động (bot)
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int x = rand.nextInt(WIDTH);
            int y = rand.nextInt(HEIGHT);
            g.fillRect(x, y, 2, 2);
        }

        // Giải phóng tài nguyên đồ họa
        g.dispose();
        return image;
    }

    /**
     * Xử lý phương thức GET, gọi processRequest
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Xử lý phương thức POST, gọi processRequest
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Trả về mô tả ngắn gọn của servlet
     */
    @Override
    public String getServletInfo() {
        return "Servlet tạo mã CAPTCHA và trả về hình ảnh";
    }
}
