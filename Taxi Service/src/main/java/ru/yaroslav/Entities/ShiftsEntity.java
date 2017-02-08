package ru.yaroslav.Entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Yaroslav on 29.12.2016.
 */
@Entity
@Table(name = "shifts", schema = "lab_db", catalog = "")
public class ShiftsEntity {
    private Timestamp timeIn;
    private Timestamp timeOut;
    private int idShift;
    private CarEntity carEntity;
    private DriverEntity driverEntity;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_car")})
    public CarEntity getCarEntity() {
        return carEntity;
    }

    public void setCarEntity(CarEntity carEntity) {
        this.carEntity = carEntity;
    }

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "id_worker")})
    public DriverEntity getDriverEntity() {
        return driverEntity;
    }

    public void setDriverEntity(DriverEntity driverEntity) {
        this.driverEntity = driverEntity;
    }



    @Basic
    @Column(name = "Time_in")
    public Timestamp getTimeIn() {
        return timeIn;
    }
    public void setTimeIn(Timestamp timeIn) {
        this.timeIn = timeIn;
    }

    @Basic
    @Column(name = "Time_out")
    public Timestamp getTimeOut() {
        return timeOut;
    }
    public void setTimeOut(Timestamp timeOut) {
        this.timeOut = timeOut;
    }

    @Id
    @Column(name = "id_shift")
    public int getIdShift() {
        return idShift;
    }
    public void setIdShift(int idShift) {
        this.idShift = idShift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShiftsEntity that = (ShiftsEntity) o;

        if (idShift != that.idShift) return false;
        if (timeIn != null ? !timeIn.equals(that.timeIn) : that.timeIn != null) return false;
        if (timeOut != null ? !timeOut.equals(that.timeOut) : that.timeOut != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timeIn != null ? timeIn.hashCode() : 0;
        result = 31 * result + (timeOut != null ? timeOut.hashCode() : 0);
        result = 31 * result + idShift;
        return result;
    }
}
