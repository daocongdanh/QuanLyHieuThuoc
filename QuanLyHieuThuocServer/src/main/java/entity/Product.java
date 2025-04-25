/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import enums.ProductType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author daoducdanh
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {
    
    @Id
    @Column(name = "product_id")
    private String productId;
    
    @Column(name = "name")
    @Nationalized
    private String name;
    
    @Column(name = "registration_number")
    @Nationalized
    private String registrationNumber;
    
    @Column(name = "active_ingredient")
    @Nationalized
    private String activeIngredient;
    
    @Column(name = "dosage")
    @Nationalized
    private String dosage;
    
    @Column(name = "packaging")
    @Nationalized
    private String packaging;
    
    @Column(name = "country_of_origin")
    @Nationalized
    private String countryOfOrigin;
    
    @Column(name = "manufacturer")
    @Nationalized
    private String manufacturer;
    
    @Column(name = "purchase_price")
    private double purchasePrice;
    
    @Column(name = "selling_price")
    private double sellingPrice;
    
    @Column(name = "active")
    private boolean active;
    
    @Column(name = "vat")
    private double VAT = 0.1;
    
    @Column(name = "image")
    private String image;
    
    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    
    @OneToMany( mappedBy = "product" ,cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    private List<Batch> listBatch;

    @ManyToOne
    @JoinColumn(  name = "unit_id")
    private Unit unit;
    
    public Product(){
        
    }

    public Product(String productId, String name, String registrationNumber, String activeIngredient, String dosage, 
            String packaging, String countryOfOrigin, String manufacturer, double purchasePrice, 
            double sellingPrice, boolean active, ProductType productType, String image, Unit unit) {
        this.productId = productId;
        setName(name);
        setRegistrationNumber(registrationNumber);
        this.activeIngredient = activeIngredient;
        this.dosage = dosage;
        this.packaging = packaging;
        this.countryOfOrigin = countryOfOrigin;
        this.manufacturer = manufacturer;
        setPurchasePrice(purchasePrice);
        setSellingPrice(sellingPrice);
        this.active = active;
        this.productType = productType;
        this.unit = unit;
        setImage(image);
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<Batch> getListBatch() {
        return listBatch;
    }

    public void setListBatch(List<Batch> listBatch) {
        if (listBatch.size() < 1) {
            throw new RuntimeException("Danh sách lô hàng không được rỗng!");
        }
        this.listBatch = listBatch;
    }
    
    public String getProductId() {
        return productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        if (image.equals("")) {
            throw new RuntimeException("Hình ảnh sản phẩm không được rỗng!");
        }
        this.image = image;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.equals("")) {
            throw new RuntimeException("Tên sản phẩm không được rỗng!");
        }
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        if (registrationNumber.equals("")) {
            throw new RuntimeException("Số đăng ký không được rỗng!");
        }
        this.registrationNumber = registrationNumber;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        if (purchasePrice <= 0) {
            throw new RuntimeException("Giá nhập phải > 0!");
        }
        this.purchasePrice = purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        if (sellingPrice <= 0) {
            throw new RuntimeException("Giá bán phải > 0!");
        }
        this.sellingPrice = sellingPrice;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getVAT() {
        return VAT;
    }

    public double getPrice(){
        return this.getSellingPrice() * (1 + this.VAT);
    }
    
    public double getPurchasePriceVAT() {
        return this.getPurchasePrice() * (1 + this.VAT);
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    
    
    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", name=" + name + ", registrationNumber=" + registrationNumber + ", activeIngredient=" + activeIngredient + ", dosage=" + dosage + ", packaging=" + packaging + ", countryOfOrigin=" + countryOfOrigin + ", manufacturer=" + manufacturer + ", purchasePrice=" + purchasePrice + ", sellingPrice=" + sellingPrice + ", active=" + active + ", VAT=" + VAT + ", productType=" + productType + '}';
    }
    
    
}
