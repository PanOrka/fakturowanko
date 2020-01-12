package com.fakturowanko;

/**
 * klasa obslugujaca produkty
 * @author dszyd
 */
public class Product {

    /**
     * id produktu
     */
    private int productId;

    /**
     * nazwa produktu
     */
    private String name;

    /**
     * cena produktu
     */
    private double price;

    /**
     * czy w sprzedazy
     */
    private boolean sold;

    /**
     * konstruktor produktow
     * @param productId id produktu
     * @param name nazwa produktu
     * @param price cena produktu
     */
    Product(int productId, String name, double price){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.sold = true;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

}
