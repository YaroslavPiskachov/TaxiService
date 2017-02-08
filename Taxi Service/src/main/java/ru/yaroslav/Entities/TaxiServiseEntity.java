package ru.yaroslav.Entities;

import javax.persistence.*;

/**
 * Created by Yaroslav on 29.12.2016.
 */
@Entity
@Table(name = "taxi_servise", schema = "lab_db", catalog = "")
public class TaxiServiseEntity {
    private int idService;
    private String adress;
    private String phoneNumber;
    private String city;

    @Id
    @Column(name = "id_service")
    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    @Basic
    @Column(name = "Adress")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Basic
    @Column(name = "Phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiServiseEntity that = (TaxiServiseEntity) o;

        if (idService != that.idService) return false;
        if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idService;
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
