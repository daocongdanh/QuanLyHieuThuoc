/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javax.swing.JPanel;

/**
 *
 * @author Hoang
 */
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ViewPdfPanel extends JPanel {

    private JPanel pagesPanel; // Panel chứa các trang PDF
    private JScrollPane scrollPane; // Thanh cuộn
    private List<BufferedImage> pages; // Danh sách các trang PDF
    private int type;

    public ViewPdfPanel( int type ) {
        setLayout(new BorderLayout());

        pagesPanel = new JPanel();
        pagesPanel.setLayout(new BoxLayout(pagesPanel, BoxLayout.Y_AXIS)); // Sắp xếp các trang theo chiều dọc

        scrollPane = new JScrollPane(pagesPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Tăng tốc độ cuộn
        add(scrollPane, BorderLayout.CENTER);

        File fontFile;
        if ( type==1 ){
            fontFile = new File("data/manual/hdsd-admin.pdf");
        }
        else {
            fontFile = new File("data/manual/hdsd-user.pdf");
        }
        displayPDF(fontFile);
    }

    private void displayPDF(File pdfFile) {
        try {
            pages = loadPDFPages(pdfFile);

            pagesPanel.removeAll();

            // Hiển thị các trang PDF
            for (BufferedImage pageImage : pages) {
                JLabel pageLabel = new JLabel(new ImageIcon(pageImage));
                pagesPanel.add(pageLabel);
                pagesPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa các trang
            }

            // Làm mới giao diện
            pagesPanel.revalidate();
            pagesPanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị file PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<BufferedImage> loadPDFPages(File pdfFile) throws Exception {
        List<BufferedImage> pageImages = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFRenderer renderer = new PDFRenderer(document);

            int totalPages = document.getNumberOfPages();
            List<Future<BufferedImage>> futures = new ArrayList<>();

            for (int i = 0; i < totalPages; i++) {
                final int pageIndex = i;
                futures.add(executor.submit(() -> {
                    BufferedImage originalImage = renderer.renderImage(pageIndex, 2.5f);
                    return originalImage;
                }));
            }

            for (Future<BufferedImage> future : futures) {
                try {
                    pageImages.add(future.get(10, TimeUnit.SECONDS));
                } catch (TimeoutException e) {
                    System.err.println("Render timeout for page: " + future);
                    future.cancel(true);
                } catch (InterruptedException | ExecutionException e) {
                    System.err.println("Error when rendering page: " + e.getMessage());
                }
            }

        } finally {
            executor.shutdown(); // Đảm bảo đóng executor sau khi xử lý xong
        }
        return pageImages;
    }

}
