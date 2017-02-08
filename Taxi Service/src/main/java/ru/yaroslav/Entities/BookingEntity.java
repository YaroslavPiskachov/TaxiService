package ru.yaroslav.Entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Yaroslav on 29.12.2016.
 */
@Entity
@Table(name = "booking", schema = "lab_db", catalog = "")
public class BookingEntity {
    private Double distance;
    private Double price;
    private Integer countPassangers;
    private Timestamp time;
    private String addressArrive;
    private String addressDeparture;
    private int idBooking;

    private ShiftsEntity shiftsEntity;


    @ManyToOne
    @JoinColumn(name = "id_shift")
    public ShiftsEntity getShiftsEntity() {
        return shiftsEntity;
    }

    public void setShiftsEntity(ShiftsEntity shiftsEntity) {
        this.shiftsEntity = shiftsEntity;
    }

    @Basic
    @Column(name = "Distance")
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Basic
    @Column(name = "Price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Count_passangers")
    public Integer getCountPassangers() {
        return countPassangers;
    }

    public void setCountPassangers(Integer countPassangers) {
        this.countPassangers = countPassangers;
    }

    @Basic
    @Column(name = "Time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "Address_arrive")
    public String getAddressArrive() {
        return addressArrive;
    }

    public void setAddressArrive(String addressArrive) {
        this.addressArrive = addressArrive;
    }

    @Basic
    @Column(name = "Address_departure")
    public String getAddressDeparture() {
        return addressDeparture;
    }

    public void setAddressDeparture(String addressDeparture) {
        this.addressDeparture = addressDeparture;
    }

    @Id
    @Column(name = "id_booking")
    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingEntity that = (BookingEntity) o;

        if (idBooking != that.idBooking) return false;
        if (distance != null ? !distance.equals(that.distance) : that.distance != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (countPassangers != null ? !countPassangers.equals(that.countPassangers) : that.countPassangers != null)
            return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (addressArrive != null ? !addressArrive.equals(that.addressArrive) : that.addressArrive != null)
            return false;
        if (addressDeparture != null ? !addressDeparture.equals(that.addressDeparture) : that.addressDeparture != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = distance != null ? distance.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (countPassangers != null ? countPassangers.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (addressArrive != null ? addressArrive.hashCode() : 0);
        result = 31 * result + (addressDeparture != null ? addressDeparture.hashCode() : 0);
        result = 31 * result + idBooking;
        return result;
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "distance=" + distance +
                ", price=" + price +
                ", countPassangers=" + countPassangers +
                ", time=" + time +
                ", addressArrive='" + addressArrive + '\'' +
                ", addressDeparture='" + addressDeparture + '\'' +
                ", idBooking=" + idBooking +
                ", shiftsEntity=" + shiftsEntity +
                '}';
    }
}
