package com.fakturowanko;

import java.util.ArrayList;

/**
 * klasa obslugujaca faktury
 * @author dszyd
 *
 */
public class Invoice {

    /**
     * id faktury
     */
    private int invoiceId;

    /**
     * id klienta
     */
    private int clientId;

    /**
     * lista produktow z ich ilosciami
     */
    protected ArrayList<ProductQuantity> productQ = new ArrayList<>();

    /**
     * tworzenie nowej faktury
     * @param clientId id klienta
     */
    Invoice(int invoiceId, int clientId){
        this.clientId = clientId;
        this.invoiceId = invoiceId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getClientId() {
        return clientId;
    }

    public void addProduct(Product product, int quantity) {
        ProductQuantity newOne = new ProductQuantity(product.getProductId(), quantity);
        productQ.add(newOne);
    }

    protected int getProductsQuantity() {
        return productQ.size();
    }

    protected int getProductId(int index) {
        return productQ.get(index).getProductId();
    }

}
