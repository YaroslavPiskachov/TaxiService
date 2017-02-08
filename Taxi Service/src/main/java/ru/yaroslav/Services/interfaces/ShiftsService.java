package ru.yaroslav.Services.interfaces;

import ru.yaroslav.Entities.DriverEntity;
import ru.yaroslav.Entities.ShiftsEntity;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Yaroslav on 09.01.2017.
 */
public interface ShiftsService {
    public ShiftsEntity getById(int id);
    public List<ShiftsEntity> getShiftsByDay(Timestamp timestamp);
    public List<ShiftsEntity> getShiftsByServiceId(int ServiceId);
    public void addShift(ShiftsEntity shiftsEntity);
    public List<ShiftsEntity> getShiftByDriverId(int worker_id);
    public void update(ShiftsEntity shiftsEntity);
    public boolean isEditingDriverNeeded( ShiftsEntity shiftsEntity);
    public List<ShiftsEntity> getShiftByTime(Timestamp time);
    public void deleteShift(ShiftsEntity shiftsEntity);
    public List<ShiftsEntity> getShiftByCarId(int car_id);
    public boolean isEditingCarNeeded(ShiftsEntity shiftsEntity);
}
