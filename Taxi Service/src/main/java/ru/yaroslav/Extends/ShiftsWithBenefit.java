package ru.yaroslav.Extends;

import java.sql.Timestamp;

/**
 * Created by Yaroslav on 09.01.2017.
 */
public class ShiftsWithBenefit {
    private Timestamp time_in;
    private Timestamp time_out;
    private int benefit;
    private String nameOfDriver;
    private String carModel;
    private double V_gasoline;

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public double getV_gasoline() {
        return V_gasoline;
    }

    public void setV_gasoline(double v_gasoline) {
        V_gasoline = v_gasoline;
    }

    public ShiftsWithBenefit(Timestamp time_in, Timestamp time_out, int benefit, String nameOfDriver, String carModel, double v_gasoline) {
        this.time_in = time_in;
        this.time_out = time_out;
        this.benefit = benefit;
        this.nameOfDriver = nameOfDriver;
        this.carModel = carModel;
        V_gasoline = v_gasoline;
    }

    public Timestamp getTime_in() {
        return time_in;
    }

    public void setTime_in(Timestamp time_in) {
        this.time_in = time_in;
    }

    public Timestamp getTime_out() {
        return time_out;
    }

    public void setTime_out(Timestamp time_out) {
        this.time_out = time_out;
    }

    public int getBenefit() {
        return benefit;
    }

    public void setBenefit(int benefit) {
        this.benefit = benefit;
    }

    public String getNameOfDriver() {
        return nameOfDriver;
    }

    public void setNameOfDriver(String nameOfDriver) {
        this.nameOfDriver = nameOfDriver;
    }
}
