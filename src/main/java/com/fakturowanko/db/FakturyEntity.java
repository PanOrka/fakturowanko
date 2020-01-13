package com.fakturowanko.db;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "faktury", schema = "fakturowanie", catalog = "")
public class FakturyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_faktury", unique = true, nullable = false)
    private Integer idFaktury;

    @Column(name = "data")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "id_klienta", referencedColumnName = "id_klienta")
    private KlientEntity idKlienta;

    public FakturyEntity() {

    }

    public FakturyEntity(KlientEntity idKlienta, Date data) {
        this.idFaktury = null;
        this.idKlienta = idKlienta;
        this.data = data;
    }

    public Integer getIdFaktury() {
        return idFaktury;
    }

    public void setIdFaktury(Integer idFaktury) {
        this.idFaktury = idFaktury;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public KlientEntity getIdKlienta() {
        return idKlienta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FakturyEntity that = (FakturyEntity) o;
        return Objects.equals(idFaktury, that.idFaktury) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFaktury, data);
    }
}
