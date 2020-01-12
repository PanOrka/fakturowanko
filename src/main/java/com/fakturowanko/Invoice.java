package com.fakturowanko;

import java.util.ArrayList;
import java.util.Date;

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
     * data zakupu
     */
    protected Date data;

    /**
     * tworzenie nowej faktury
     * @param clientId id klienta
     */
    Invoice(int invoiceId, int clientId){
        this.clientId = clientId;
        this.invoiceId = invoiceId;
        this.data = new Date();
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getClientId() {
        return clientId;
    }

    //TODO przy pobieraniu faktury z bazy trzeba bedzie ta funkcja przypisac jej odpowiednie rekordy z ilosc_produktow
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
