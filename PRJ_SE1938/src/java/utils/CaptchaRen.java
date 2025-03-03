/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author admin
 */
public class CaptchaRen {

 

    public BufferedImage generatePic(String captcha) {
        int width = 160, height = 35;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.createGraphics();

        // Set background color
        graphics.setColor(new Color(0, 0, 0)); // You can choose a different color
        graphics.fillRect(0, 0, width, height);

        // Draw random lines for complexity
        Random random = new Random();
        graphics.setColor(new Color(255, 255, 255)); // Line color
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1, y1, x2, y2);
        }

        // Draw text
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.setColor(new Color(255, 255, 255)); // Text color
        graphics.drawString(captcha, 45, 25);

        return bufferedImage;
    }
    
  

}
