/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.UnitDAL;
import dal.UnitDetailDAL;
import jakarta.persistence.EntityManager;
import entity.Unit;
import jakarta.persistence.EntityTransaction;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.MessageDialog;

/**
 *
 * @author daoducdanh
 */
public class UnitBUS {
    private UnitDAL unitDAL;
    private UnitDetailDAL unitDetailDAL;
    private EntityTransaction transaction;
    
    public UnitBUS(EntityManager entityManager){
        this.unitDAL = new UnitDAL(entityManager);
        this.unitDetailDAL = new UnitDetailDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }
    
    public boolean createUnit(Unit unit){
        try{
            transaction.begin();
            unitDAL.insert(unit);
            transaction.commit();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
    public boolean updateUnit(Unit unit){
        try{
            transaction.begin();
            unitDAL.update(unit);
            transaction.commit();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
    public Unit getUnitById(String id){
        return unitDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tim thấy đơn vị tính với id = " + id));
    }
    
    public List<Unit> getAllUnits(){
        return unitDAL.findAll();
    }
    
    public Unit getUnitByName(String name){
        return unitDAL.findByName(name);
    }
    
    public List<Unit> getUnitByNameSearch(String name){
        return unitDAL.findByNameSearch(name);
    }
    
    public boolean checkUnitForEdit( String unitId ){
        // check unit khong thuoc ve san pham nao
        if ( !unitDetailDAL.findByUnit(unitId).isEmpty() ){
            return false;
        } 
        return true;
    }
    
    public boolean importExcel() {
        try {
            JFileChooser chooserFile = new JFileChooser();
            List<Object> rowNotImport = new ArrayList<>();
            List<Unit> listUnitInsert = new ArrayList<>();
            chooserFile.setDialogTitle("Chọn file");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
            chooserFile.setFileFilter(fnef);
            int result = chooserFile.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File excelFile = chooserFile.getSelectedFile();
                FileInputStream excelFIS = new FileInputStream(excelFile);
                BufferedInputStream excelBIS = new BufferedInputStream(excelFIS);
                XSSFWorkbook excelImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelImport.getSheetAt(0);

                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);
                    Object stt = excelRow.getCell(0).getRawValue();
                    String name = excelRow.getCell(1).getStringCellValue();
                    if (unitDAL.findByName(name) != null) {
                        rowNotImport.add(stt);
                    } else {
                        Unit unit = new Unit(null, name);
                        listUnitInsert.add(unit);
                    }
                }
                
                if ( listUnitInsert.isEmpty() ){
                    MessageDialog.warning(null, "File không được import do dữ liệu đã tồn tại trong hệ thống !!!");
                    return false;
                }

                if (!rowNotImport.isEmpty()) {
                    if (MessageDialog.confirm(null, "Có " + rowNotImport.size() + " dòng dữ liệu không được thêm vào"
                            + ", bạn muốn tiếp tục chứ ?", "Cảnh báo")) {
                        for (Unit unit : listUnitInsert) {
                            createUnit(unit);
                        }
                        return true;
                    } else {
                        String errorRow = rowNotImport.stream().map(x -> x.toString()).collect(Collectors.joining(","));
                        MessageDialog.warning(null, "Các dòng bị lỗi là " + errorRow);
                        return false;
                    }
                } else {
                    for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                        XSSFRow excelRow = excelSheet.getRow(row);
                        String name = excelRow.getCell(1).getStringCellValue().trim();
                        Unit unit = new Unit(null, name);
                        unitDAL.insert(unit);
                    }
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Export file bị lỗi");
        }
        return false;
    }
}
