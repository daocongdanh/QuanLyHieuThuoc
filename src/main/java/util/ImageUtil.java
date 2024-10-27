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

    public static BufferedImage getImage(String imageName) {
        BufferedImage image = null;
        try (InputStream is = ImageUtil.class.getClassLoader().getResourceAsStream("img/" + imageName)) {
            if (is != null) {
                image = ImageIO.read(is);
            } else {
                System.err.println("Image not found: " + imageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static void saveImage(ImageIcon icon, String filename, String format) {
        try {
            // Chuyển đổi ImageIcon thành BufferedImage
            BufferedImage bufferedImage;
            if (icon.getImage() instanceof BufferedImage) {
                bufferedImage = (BufferedImage) icon.getImage();
            } else {
                bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
                bufferedImage.getGraphics().drawImage(icon.getImage(), 0, 0, null);
            }
            File outputFile = new File("src/main/resources/img/" + filename + "." + format); 
            ImageIO.write(bufferedImage, format, outputFile);
            System.out.println("Image saved: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
