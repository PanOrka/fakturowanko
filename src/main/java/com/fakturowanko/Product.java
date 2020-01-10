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
     * konstruktor produktow
     * @param productId id produktu
     * @param name nazwa produktu
     * @param price cena produktu
     */
    Product(int productId, String name, double price){
        this.productId = productId;
        this.name = name;
        this.price = price;
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
}
