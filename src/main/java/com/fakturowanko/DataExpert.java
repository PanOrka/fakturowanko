package com.fakturowanko;

import java.util.ArrayList;

/**
 * klasa obslugujaca baze danych
 * @author dszyd
 *
 */
public class DataExpert {

    protected ArrayList<Client> clientList;
    protected ArrayList<Product> productList;
    protected ArrayList<Invoice> invoiceList;

    DataExpert(){
        clientList = new ArrayList<>();
        productList = new ArrayList<>();
        invoiceList = new ArrayList<>();
        addClient(1, "Dominika", "Szydlo", "022899", "ul.Piastowska 34/4", "50-361 Wroclaw");
        addProduct(1, "Pad thai", 22.0);
        addProduct(2, "Krewetki", 34.50);
        addProduct(3, "Hummus", 15.0);
        addProduct(4, "Woda 0.5L", 3.90);
        addProduct(5, "Hopium Ale", 9.80);
    }

    protected void addClient(int id, String name, String surname, String nip, String adress, String city){
        Client client = new Client(id, name, surname, adress, city, nip);
        clientList.add(client);
    }

    protected void addProduct(int id, String name, double price){
        Product product = new Product(id, name, price);
        productList.add(product);
    }

    protected void addInvoice(Invoice invoice){
        invoiceList.add(invoice);
    }

    protected int getNewClientId() {
        return clientList.size()+1;
    }

    protected int getNewProductId() {
        return productList.size()+1;
    }

    protected int getNewInvoiceId() {
        return invoiceList.size()+1;
    }

    protected Product getProduct(String name) {
        for (int i=0;i<productList.size();i++) {
            if(productList.get(i).getName().equals(name)) {
                return productList.get(i);
            }
        }
        return null;
    }

    protected Client getClient(int index) {
        for(int i=0;i<clientList.size();i++) {
            if(clientList.get(i).getClientId()==index) return clientList.get(i);
        }
        return null;
    }

    protected String getProductName(int index) {
        for(int i=0;i<productList.size();i++) {
            if(productList.get(i).getProductId()==index) return productList.get(i).getName();
        }
        return null;
    }

    protected Invoice getInvoice(int index) {
        for (int i=0;i<invoiceList.size();i++) {
            if(invoiceList.get(i).getInvoiceId()==index) return invoiceList.get(i);
        }
        return null;
    }

    protected double getProductPrice(int index) {
        for (int i=0;i<productList.size();i++) {
            if(productList.get(i).getProductId()==index) return productList.get(i).getPrice();
        }
        return 0.0;
    }
}
