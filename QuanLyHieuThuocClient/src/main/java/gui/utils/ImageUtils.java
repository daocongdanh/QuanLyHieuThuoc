package gui.utils;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {

    public static byte[] imageIconToByteArray(ImageIcon imageIcon) {
        // Lấy BufferedImage từ ImageIcon
        BufferedImage bufferedImage = new BufferedImage(
            imageIcon.getIconWidth(),
            imageIcon.getIconHeight(),
            BufferedImage.TYPE_INT_ARGB
        );
        
        // Vẽ hình ảnh từ ImageIcon vào BufferedImage
        bufferedImage.getGraphics().drawImage(imageIcon.getImage(), 0, 0, null);
        
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // Ghi BufferedImage vào ByteArrayOutputStream dưới dạng PNG (có thể thay đổi định dạng)
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
