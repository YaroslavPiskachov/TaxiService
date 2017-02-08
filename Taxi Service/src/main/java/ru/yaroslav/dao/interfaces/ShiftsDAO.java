package ru.yaroslav.dao.interfaces;

import ru.yaroslav.Entities.DriverEntity;
import ru.yaroslav.Entities.ShiftsEntity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Yaroslav on 07.01.2017.
 */
public interface ShiftsDAO {
    public ShiftsEntity getById(int id);
    public List<ShiftsEntity> getShiftsByDay(Timestamp timestamp);
    public List<ShiftsEntity> getShiftsByServiceId(int ServiceId);
    public void addShift(ShiftsEntity shiftsEntity);
    public List<ShiftsEntity> getShiftByDriverId(int worker_id);
    public List<ShiftsEntity> getShiftByCarId(int car_id);
    public List<ShiftsEntity> getShiftByTime(Timestamp time);
    public void update(ShiftsEntity shiftsEntity);
    public boolean isEditingDriverNeeded(ShiftsEntity shiftsEntity);
    public boolean isEditingCarNeeded(ShiftsEntity shiftsEntity);
    public void deleteShift(ShiftsEntity shiftsEntity);
}
