package com.fakturowanko;

public class ChoosenProds {

    private int product_id;
    private String name;
    private int quantity;

    ChoosenProds(int product_id, String name, int quantity) {
        this.product_id = product_id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getProduct_id() {
        return this.product_id;
    }

    public String getName() {
        return this.name;
    }
}
