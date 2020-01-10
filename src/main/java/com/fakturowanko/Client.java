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
     * imie klienta
     */
    private String name;

    /**
     * nazwisko klienta
     */
    private String surname;

    /**
     * NIP klienta
     */
    private String nip;

    /**
     * adres klienta ulica
     */
    private String adress;

    /**
     * adres klienta miasto i kod pocztowy
     */
    private String city;


    /**
     * konstruktor tworzacy nowego klienta
     * @param clientId id klienta
     * @param name imie klienta
     * @param surname nazwisko klienta
     * @param adress adres klienta
     * @param nip nip klienta
     */
    Client (int clientId, String name, String surname, String adress, String city, String nip){
        this.clientId = clientId;
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.city = city;
        this.nip = nip;
    }

    public String getAdress() {
        return adress;
    }

    public String getNip() {
        return nip;
    }

    public String getSurname() {
        return surname;
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

