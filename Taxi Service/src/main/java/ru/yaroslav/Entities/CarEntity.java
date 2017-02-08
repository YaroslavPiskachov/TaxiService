package ru.yaroslav.Entities;

import javax.persistence.*;

/**
 * Created by Yaroslav on 29.12.2016.
 */
@Entity
@Table(name = "cars", schema = "lab_db", catalog = "")
public class CarEntity {
    private String model;
    private String numberOfCar;
    private int year;
    private int id_car;
    private double gasolineRate;
    private TaxiServiseEntity taxiServiseEntity;

    @ManyToOne
    @JoinColumn(name = "id_service")
    public TaxiServiseEntity getTaxiServiseEntity() {
        return taxiServiseEntity;
    }

    public void setTaxiServiseEntity(TaxiServiseEntity taxiServiseEntity) {
        this.taxiServiseEntity = taxiServiseEntity;
    }


    @Id
    @Column(name = "id_car")
    public int getId_car() {
        return id_car;
    }

    public void setId_car(int id_car) {
        this.id_car = id_car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity carEntity = (CarEntity) o;

        if (year != carEntity.year) return false;
        if (model != null ? !model.equals(carEntity.model) : carEntity.model != null) return false;
        return numberOfCar != null ? numberOfCar.equals(carEntity.numberOfCar) : carEntity.numberOfCar == null;
    }

    @Override
    public int hashCode() {
        int result = model != null ? model.hashCode() : 0;
        result = 31 * result + (numberOfCar != null ? numberOfCar.hashCode() : 0);
        result = 31 * result + year;
        return result;
    }

    @Basic
    @Column(name = "model")
    public String getModel() {

        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name="gasolineRate")
    public double getGasolineRate() {
        return gasolineRate;
    }

    public void setGasolineRate(double gasolineRate) {
        this.gasolineRate = gasolineRate;
    }

    @Basic
    @Column(name = "car_number")
    public String getNumberOfCar() {
        return numberOfCar;
    }

    public void setNumberOfCar(String numberOfCar) {
        this.numberOfCar = numberOfCar;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
