package dto;

import enums.ProductType;

import java.time.LocalDateTime;

public class StatsProductDTO {

    private String productName;
    private Integer quantity;
    private Double sumPrice;
    private ProductType productType;
    private LocalDateTime time;

    public StatsProductDTO() {

    }

    public StatsProductDTO(String productName, Integer quantity, Double sumPrice, LocalDateTime time, ProductType productType) {
        this.productName = productName;
        this.quantity = quantity;
        this.sumPrice = sumPrice;
        this.time = time;
        this.productType = productType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
