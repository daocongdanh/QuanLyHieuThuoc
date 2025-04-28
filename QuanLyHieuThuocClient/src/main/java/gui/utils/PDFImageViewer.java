package gui.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class PDFImageViewer extends JDialog {

    private JLabel imageLabel;

    public PDFImageViewer(byte[] pdfBytes) {
        setTitle("Hóa đơn");
        setSize(634, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        imageLabel = new JLabel();
        JScrollPane scroll = new JScrollPane(imageLabel);
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);

        displayPDFAsImage(pdfBytes);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setVisible(true);
    }

    private void displayPDFAsImage(byte[] pdfBytes) {
        try {
            PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes));
            PDFRenderer renderer = new PDFRenderer(document);
            BufferedImage image = renderer.renderImage(0, 1); // Render first page, scale 1
            document.close();

            ImageIcon icon = new ImageIcon(image);
            imageLabel.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi chi tiết nếu có
        }
    }
}
