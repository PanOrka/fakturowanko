package com.fakturowanko;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "faktury", schema = "fakturowanie", catalog = "")
public class FakturyEntity {
    private Integer idFaktury;
    private Date data;

    @Id
    @Column(name = "id_faktury")
    public Integer getIdFaktury() {
        return idFaktury;
    }

    public void setIdFaktury(Integer idFaktury) {
        this.idFaktury = idFaktury;
    }

    @Basic
    @Column(name = "data")
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
