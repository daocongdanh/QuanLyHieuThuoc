package dto;

import entity.Product;
import entity.UnitDetail;

/**
 *
 * @author Hoang
 */
public class ReturnOrderDetailDTO {

    private Product product;
    private UnitDetail unitDetail;
    private int quantityReturn;
    private String batchName;

    public ReturnOrderDetailDTO() {
    }

    public ReturnOrderDetailDTO(Product product, UnitDetail unitDetail, int quantityReturn, String batchName ) {
        this.product = product;
        this.unitDetail = unitDetail;
        this.quantityReturn = quantityReturn;
        this.batchName = batchName;
    }

    public Product getProduct() {
        return product;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
    
    

    public void setProduct(Product product) {
        this.product = product;
    }

    public UnitDetail getUnitDetail() {
        return unitDetail;
    }

    public void setUnitDetail(UnitDetail unitDetail) {
        this.unitDetail = unitDetail;
    }

    public int getQuantityReturn() {
        return quantityReturn;
    }

    public void setQuantityReturn(int quantityReturn) {
        this.quantityReturn = quantityReturn;
    }

}
