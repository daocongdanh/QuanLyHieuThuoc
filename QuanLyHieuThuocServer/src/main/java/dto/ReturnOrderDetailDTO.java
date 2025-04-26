package dto;

import entity.Product;

import java.io.Serializable;

/**
 *
 * @author Hoang
 */
public class ReturnOrderDetailDTO implements Serializable {

    private Product product;
    private int quantityReturn;
    private String reason;

    public ReturnOrderDetailDTO() {
    }

    public ReturnOrderDetailDTO(Product product, int quantityReturn, String reason) {
        this.product = product;
        this.quantityReturn = quantityReturn;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityReturn() {
        return quantityReturn;
    }

    public void setQuantityReturn(int quantityReturn) {
        this.quantityReturn = quantityReturn;
    }

}
