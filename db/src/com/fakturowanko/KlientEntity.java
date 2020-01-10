package com.fakturowanko;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "klient", schema = "fakturowanie", catalog = "")
public class KlientEntity {
    private Integer idKlienta;
    private String nazwa;
    private String nip;
    private String miasto;
    private String kodPocztowy;
    private String adres;

    @Id
    @Column(name = "id_klienta")
    public Integer getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(Integer idKlienta) {
        this.idKlienta = idKlienta;
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
    @Column(name = "nip")
    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @Basic
    @Column(name = "miasto")
    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    @Basic
    @Column(name = "kod_pocztowy")
    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    @Basic
    @Column(name = "adres")
    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KlientEntity that = (KlientEntity) o;
        return Objects.equals(idKlienta, that.idKlienta) &&
                Objects.equals(nazwa, that.nazwa) &&
                Objects.equals(nip, that.nip) &&
                Objects.equals(miasto, that.miasto) &&
                Objects.equals(kodPocztowy, that.kodPocztowy) &&
                Objects.equals(adres, that.adres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKlienta, nazwa, nip, miasto, kodPocztowy, adres);
    }
}
