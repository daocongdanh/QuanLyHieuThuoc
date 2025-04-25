/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

public enum ProductType {
    MEDICINE("Thuốc"),
    MEDICALSUPPLIES("Vật tư y tế"),
    DIETARYSUPPLEMENT("Thực phẩm chức năng"),
    BABYCARE("Chăm sóc trẻ em"),
    MEDICALEQUIPMENT("Thiết bị y tế");

    private final String description;

    ProductType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ProductType fromDescription(String description) {
        for (ProductType productType : ProductType.values()) {
            if (productType.getDescription().equalsIgnoreCase(description)) {
                return productType;
            }
        }
        throw new IllegalArgumentException("Không tìm thấy enum với mô tả: " + description);
    }
}
