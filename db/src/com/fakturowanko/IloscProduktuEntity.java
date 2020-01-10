package com.fakturowanko;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ilosc_produktu", schema = "fakturowanie", catalog = "")
public class IloscProduktuEntity {
    private Integer ilosc;
    private Double cenaZakupu;

    @Basic
    @Column(name = "ilosc")
    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    @Basic
    @Column(name = "cena_zakupu")
    public Double getCenaZakupu() {
        return cenaZakupu;
    }

    public void setCenaZakupu(Double cenaZakupu) {
        this.cenaZakupu = cenaZakupu;
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
