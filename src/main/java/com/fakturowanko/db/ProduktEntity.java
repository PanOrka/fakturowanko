package com.fakturowanko.db;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "produkt", schema = "fakturowanie", catalog = "")
public class ProduktEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_produktu", unique = true, nullable = false)
    private Integer idProduktu;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "cena")
    private Double cena;

    @Column(name = "sprzedawany")
    private boolean sprzedawany;


    public ProduktEntity() {

    }

    public ProduktEntity(String nazwa, Double cena, boolean sprzedawany) {
        this.idProduktu = null;
        this.nazwa = nazwa;
        this.cena = cena;
        this.sprzedawany = sprzedawany;
    }

    public Integer getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(Integer idProduktu) {
        this.idProduktu = idProduktu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public boolean getSprzedawany() {
        return sprzedawany;
    }

    public void setSprzedawany(boolean sprzedawany) {
        this.sprzedawany = sprzedawany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduktEntity that = (ProduktEntity) o;
        return Objects.equals(idProduktu, that.idProduktu) &&
                Objects.equals(nazwa, that.nazwa) &&
                Objects.equals(cena, that.cena) &&
                Objects.equals(sprzedawany, that.sprzedawany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduktu, nazwa, cena, sprzedawany);
    }
}
