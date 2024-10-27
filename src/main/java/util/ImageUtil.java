/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Hoang
 */
public class ImageUtil {

    public static BufferedImage getImage(String fileName) {
        // Đường dẫn đến tệp hình ảnh
        String path = "src/main/resources/img/" + fileName + ".jpg";

        BufferedImage image = null;
        try {
            // Đọc hình ảnh từ tệp
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc hình ảnh: " + e.getMessage());
        }

        return image;
    }

    public static void saveImageIcon(ImageIcon imageIcon, String fileName) {
        // Chuyển đổi ImageIcon thành BufferedImage
        BufferedImage bufferedImage = new BufferedImage(
                imageIcon.getIconWidth(),
                imageIcon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);

        // Vẽ ImageIcon lên BufferedImage
        bufferedImage.getGraphics().drawImage(imageIcon.getImage(), 0, 0, null);

        // Đường dẫn nơi sẽ lưu hình ảnh
        String path = "src/main/resources/img/" + fileName + ".jpg";

        try {
            // Lưu hình ảnh
            ImageIO.write(bufferedImage, "jpg", new File(path));
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu hình ảnh: " + e.getMessage());
        }
    }

}
