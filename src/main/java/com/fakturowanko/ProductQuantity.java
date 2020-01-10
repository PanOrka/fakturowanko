package com.fakturowanko;

/**
 * klasa obslugujaca ilosc produktu o danym id
 * @author dszyd
 *
 */
public class ProductQuantity {
    /**
     * id produktu
     */
    private int productId;

    /**
     * ilosc produktu
     */
    private int productQuantity;

    /**
     * konstruktor klasy
     * @param productId id produktu
     * @param productQuantity ilosc produktu
     */
    ProductQuantity(int productId, int productQuantity){
        this.setProductId(productId);
        this.setProductQuantity(productQuantity);
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
