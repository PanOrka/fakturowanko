package com.fakturowanko;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "produkt", schema = "fakturowanie", catalog = "")
public class ProduktEntity {
    private Integer idProduktu;
    private String nazwa;
    private Double cena;
    private Byte sprzedawany;

    @Id
    @Column(name = "id_produktu")
    public Integer getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(Integer idProduktu) {
        this.idProduktu = idProduktu;
    }

    @Basic
    @Column(name = "nazwa")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Basic
    @Column(name = "cena")
    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @Basic
    @Column(name = "sprzedawany")
    public Byte getSprzedawany() {
        return sprzedawany;
    }

    public void setSprzedawany(Byte sprzedawany) {
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
