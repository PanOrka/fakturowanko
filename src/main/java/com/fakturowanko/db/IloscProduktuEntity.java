package com.fakturowanko.db;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ilosc_produktu", schema = "fakturowanie", catalog = "")
public class IloscProduktuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_faktury", referencedColumnName = "id_faktury")
    private FakturyEntity id_faktury;

    @ManyToOne
    @JoinColumn(name = "id_produktu", referencedColumnName = "id_produktu")
    private ProduktEntity id_produktu;

    @Column(name = "ilosc")
    private Integer ilosc;

    @Column(name = "cena_zakupu")
    private Double cenaZakupu;

    public IloscProduktuEntity() {

    }

    public IloscProduktuEntity(FakturyEntity id_faktury, ProduktEntity id_produktu, Integer ilosc, Double cenaZakupu) {
        this.id = null;
        this.id_faktury = id_faktury;
        this.id_produktu = id_produktu;
        this.ilosc = ilosc;
        this.cenaZakupu = cenaZakupu;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    public Double getCenaZakupu() {
        return cenaZakupu;
    }

    public void setCenaZakupu(Double cenaZakupu) {
        this.cenaZakupu = cenaZakupu;
    }

    public ProduktEntity getProdukt() {
        return this.id_produktu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IloscProduktuEntity that = (IloscProduktuEntity) o;
        return Objects.equals(ilosc, that.ilosc) &&
                Objects.equals(cenaZakupu, that.cenaZakupu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ilosc, cenaZakupu);
    }
}
