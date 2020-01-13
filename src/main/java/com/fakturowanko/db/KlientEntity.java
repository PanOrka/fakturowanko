package com.fakturowanko.db;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "klient", schema = "fakturowanie", catalog = "")
public class KlientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_klienta", unique = true, nullable = false)
    private Integer idKlienta;

    @Column(name = "nazwa", nullable = false)
    private String nazwa;

    @Column(name = "nip")
    private String nip;

    @Column(name = "miasto", nullable = false)
    private String miasto;

    @Column(name = "kod_pocztowy", nullable = false)
    private String kodPocztowy;

    @Column(name = "adres", nullable = false)
    private String adres;

    public KlientEntity() {

    }

    public KlientEntity(String nazwa, String nip, String miasto, String kod_pocztowy, String adres) {
        this.nazwa = nazwa;
        this.nip = nip;
        this.miasto = miasto;
        this.kodPocztowy = kod_pocztowy;
        this.adres = adres;
    }

    public Integer getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(Integer idKlienta) {
        this.idKlienta = idKlienta;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

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
