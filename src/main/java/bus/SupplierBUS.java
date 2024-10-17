/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.SupplierDAL;
import entity.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.MessageDialog;

/**
 *
 * @author daoducdanh
 */
public class SupplierBUS {
    private SupplierDAL supplierDAL;
    private EntityTransaction transaction;
    private Object CellType;
    
    public SupplierBUS(EntityManager entityManager){
        this.supplierDAL = new SupplierDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }
    
    public boolean createrSupplier(Supplier supplier){
        try{
            transaction.begin();
            supplierDAL.insert(supplier);
            transaction.commit();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
    public boolean updateSupplier(Supplier supplier){
        try{
            transaction.begin();
            supplierDAL.update(supplier);
            transaction.commit();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
    
    public Supplier getSupplierById(String id){
        return supplierDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhà cung cấp với id = " + id));
    }
    
    public List<Supplier> getAllSuppliers(){
        return supplierDAL.findAll();
    }
    
    public Supplier getSupByName(String name){
        return supplierDAL.findByName(name);
    }
    
    public List<Supplier> searchSuppliersByText(String text) {
        return supplierDAL.searchSuppliersByText(text);
    }
    
//    public boolean importExcel() {
//    try {
//        JFileChooser chooserFile = new JFileChooser();
//        List<Object> rowNotImport = new ArrayList<>();
//        List<Supplier> listSupplierInsert = new ArrayList<>();
//        chooserFile.setDialogTitle("Chọn file");
//        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
//        chooserFile.setFileFilter(fnef);
//        int result = chooserFile.showOpenDialog(null);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File excelFile = chooserFile.getSelectedFile();
//            FileInputStream excelFIS = new FileInputStream(excelFile);
//            BufferedInputStream excelBIS = new BufferedInputStream(excelFIS);
//            XSSFWorkbook excelImport = new XSSFWorkbook(excelBIS);
//            XSSFSheet excelSheet = excelImport.getSheetAt(0);
//
//            for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
//                XSSFRow excelRow = excelSheet.getRow(row);
//                String supplierId = excelRow.getCell(0).getStringCellValue();
//                String name = excelRow.getCell(1).getStringCellValue();
//                String address = excelRow.getCell(2).getStringCellValue();
//                String phone = excelRow.getCell(3).getStringCellValue();
//                String email = excelRow.getCell(4).getStringCellValue();
//                String taxCode = excelRow.getCell(5).getStringCellValue();
//
//                // Kiểm tra số điện thoại hợp lệ
//                if (phone.length() != 10 || !phone.matches("\\d+")) {
//                    rowNotImport.add(supplierId);
//                    continue; // Bỏ qua hàng này nếu số điện thoại không hợp lệ
//                }
//
//                // Kiểm tra xem nhà cung cấp đã tồn tại hay chưa
//                if (supplierDAL.findById(supplierId) != null) {
//                    rowNotImport.add(supplierId);
//                } else {
//                    Supplier supplier = new Supplier(supplierId, name, address, phone, email, taxCode);
//                    listSupplierInsert.add(supplier);
//                }
//            }
//            
//            if (listSupplierInsert.isEmpty()) {
//                MessageDialog.warring(null, "File không được import do dữ liệu đã tồn tại trong hệ thống !!!");
//                return false;
//            }
//
//            if (!rowNotImport.isEmpty()) {
//                if (MessageDialog.confirm(null, "Có " + rowNotImport.size() + " dòng dữ liệu không được thêm vào. Bạn muốn tiếp tục không?", "Cảnh báo")) {
//                    for (Supplier supplier : listSupplierInsert) {
//                        supplierDAL.insert(supplier); // Gọi phương thức thêm nhà cung cấp
//                    }
//                    return true;
//                } else {
//                    String errorRow = rowNotImport.stream().map(Object::toString).collect(Collectors.joining(","));
//                    MessageDialog.warring(null, "Các dòng bị lỗi là " + errorRow);
//                    return false;
//                }
//            } else {
//                for (Supplier supplier : listSupplierInsert) {
//                    supplierDAL.insert(supplier); // Gọi phương thức thêm nhà cung cấp
//                }
//                return true;
//            }
//        }
//    } catch (Exception e) {
//        throw new RuntimeException("Import file bị lỗi: " + e.getMessage());
//    }
//    return false;
//}
        

   
   public void exportToExcel(String filePath) {
    Workbook workbook = null;
    FileOutputStream fileOut = null;
    try {
        workbook = new XSSFWorkbook();
        fileOut = new FileOutputStream(filePath);
        Sheet sheet = workbook.createSheet("Suppliers");
        List<Supplier> suppliers = getAllSuppliers();

        // Xuất tên cột
        Row headerRow = sheet.createRow(0);
        String[] columnNames = {"Supplier ID", "Name", "Address", "Phone", "Email", "Tax Code"};
        for (int i = 0; i < columnNames.length; i++) {
            headerRow.createCell(i).setCellValue(columnNames[i]);
        }

        // Xuất dữ liệu
        for (int i = 0; i < suppliers.size(); i++) {
            Supplier supplier = suppliers.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(supplier.getSupplierId());
            row.createCell(1).setCellValue(supplier.getName());
            row.createCell(2).setCellValue(supplier.getAddress());
            row.createCell(3).setCellValue(supplier.getPhone());
            row.createCell(4).setCellValue(supplier.getEmail());
            row.createCell(5).setCellValue(supplier.getTaxCode());
        }

        workbook.write(fileOut);
        JOptionPane.showMessageDialog(null, "Xuất Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Lỗi khi xuất Excel: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
   }
         
}
