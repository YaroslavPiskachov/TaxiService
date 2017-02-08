package ru.yaroslav.Entities;

import javax.persistence.*;

/**
 * Created by Yaroslav on 07.01.2017.
 */
@Entity
@Table(name = "driver", schema = "lab_db", catalog = "")
public class DriverEntity {
    private String fullName;
    private Integer expiriance;
    private int idWorker;
    private String mobNumber;
    private TaxiServiseEntity taxiServiseEntity;

    @ManyToOne
    @JoinColumn(name = "id_service")
    public TaxiServiseEntity getTaxiServiseEntity() {
        return taxiServiseEntity;
    }

    public void setTaxiServiseEntity(TaxiServiseEntity taxiServiseEntity) {
        this.taxiServiseEntity = taxiServiseEntity;
    }

    @Basic
    @Column(name = "mobNumber")
    public String getMobNumber() {
        return mobNumber;
    }

    public void setMobNumber(String mobNumber) {
        this.mobNumber = mobNumber;
    }

    @Basic
    @Column(name = "Full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "Expiriance")
    public Integer getExpiriance() {
        return expiriance;
    }

    public void setExpiriance(Integer expiriance) {
        this.expiriance = expiriance;
    }

    @Id
    @Column(name = "id_worker")

    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverEntity that = (DriverEntity) o;

        if (idWorker != that.idWorker) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (expiriance != null ? !expiriance.equals(that.expiriance) : that.expiriance != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fullName != null ? fullName.hashCode() : 0;
        result = 31 * result + (expiriance != null ? expiriance.hashCode() : 0);
        result = 31 * result + idWorker;
        return result;
    }
}
