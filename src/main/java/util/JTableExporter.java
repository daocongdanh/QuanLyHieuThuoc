package util;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JTableExporter {

    public static void openFile(String file) throws IOException {
        File path = new File(file);
        Desktop.getDesktop().open(path);
    }

    public static void exportJTableToExcel(JTable table) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn đường dẫn lưu file Excel");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XLSX files", "xlsx");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int userChoice = fileChooser.showSaveDialog(null);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                TableModel model = table.getModel();
                Workbook workbook;

                // Khởi tạo workbook cho định dạng xlsx
                if (filePath.endsWith(".xlsx")) {
                    workbook = new XSSFWorkbook();
                } else {
                    workbook = new HSSFWorkbook();
                }

                Sheet sheet = workbook.createSheet("Sheet1");

                // Tạo dòng tiêu đề
                CellStyle cellStyle = createStyleForHeader(sheet);
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < model.getColumnCount(); i++) {
                    Cell headerCell = headerRow.createCell(i);
                    headerCell.setCellValue(model.getColumnName(i));
                    headerCell.setCellStyle(cellStyle);
                }

                // Tạo dòng dữ liệu
                for (int i = 0; i < model.getRowCount(); i++) {
                    Row dataRow = sheet.createRow(i + 1);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Cell dataCell = dataRow.createCell(j);
                        Object value = model.getValueAt(i, j);
                        if (value != null) {
                            dataCell.setCellValue(value.toString());
                        }
                    }
                }

                // Điều chỉnh kích thước cột
                for (int i = 0; i < model.getColumnCount(); i++) {
                    sheet.autoSizeColumn(i);
                }

                // Ghi dữ liệu ra file
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }

                openFile(filePath);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi đọc file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Tạo font chữ
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);  // BOLDWEIGHT cho Apache POI 3.9
        font.setFontHeightInPoints((short) 14);

        // Tạo CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        return cellStyle;
    }
}