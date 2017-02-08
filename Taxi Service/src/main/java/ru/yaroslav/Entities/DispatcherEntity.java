package ru.yaroslav.Entities;

import javax.persistence.*;

/**
 * Created by Yaroslav on 07.01.2017.
 */
@Entity
@Table(name = "dispatcher", schema = "lab_db", catalog = "")
public class DispatcherEntity {
    private String fullName;
    private TaxiServiseEntity taxiServiseEntity;
    private String personalCode;
    private int age;

    @ManyToOne
    @JoinColumn(name = "id_service")
    public TaxiServiseEntity getTaxiServiseEntity() {
        return taxiServiseEntity;
    }


    public void setTaxiServiseEntity(TaxiServiseEntity taxiServiseEntity) {
        this.taxiServiseEntity = taxiServiseEntity;
    }

    @Basic
    @Column(name = "Age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Id
    @Column(name = "PersonalCode")
    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    @Basic
    @Column(name = "Full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DispatcherEntity that = (DispatcherEntity) o;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;

        return true;
    }


}
