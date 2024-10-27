/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.oned.Code128Writer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author daoducdanh
 */
public class GenerateBarcode {

    public void generate(String text, String fileName) {
        Code128Writer barcodeWriter = new Code128Writer();
        int width = 300;
        int height = 100;

        try {
            BitMatrix bitMatrix = barcodeWriter.encode(text, BarcodeFormat.CODE_128, width, height);
            Path path = Paths.get(fileName);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            System.out.println("Barcode generated successfully at: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error generating barcode: " + e.getMessage());
        }
    }
}
