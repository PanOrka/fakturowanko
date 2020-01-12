package com.fakturowanko;

/**
 * klasa obslugujaca dane klientow
 */
public class Client {
    /**
     * id klienta w bazie
     */
    private int clientId;

    /**
     * nazwa klienta
     */
    private String name;

    /**
     * NIP klienta
     */
    private String nip;

    /**
     * adres klienta ulica
     */
    private String adress;

    /**
     * adres klienta miasto
     */
    private String city;

    /**
     * kod pocztowy klienta
     */
    private String postal_code;

    /**
     * konstruktor tworzacy nowego klienta
     * @param clientId id klienta
     * @param name imie klienta
     * @param adress adres klienta
     * @param nip nip klienta
     */
    Client (int clientId, String name, String adress, String city, String postal_code, String nip){
        this.clientId = clientId;
        this.name = name;
        this.adress = adress;
        this.city = city;
        this.postal_code = postal_code;
        this.nip = nip;
    }

    public String getAdress() {
        return adress;
    }

    public String getNip() {
        return nip;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getName() {
        return name;
    }

    public int getClientId() {
        return clientId;
    }

    public String getCity() {
        return city;
    }
}

