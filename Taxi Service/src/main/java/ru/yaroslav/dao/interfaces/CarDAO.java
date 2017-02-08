package ru.yaroslav.dao.interfaces;

import ru.yaroslav.Entities.CarEntity;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Yaroslav on 19.01.2017.
 */
public interface CarDAO {
    public CarEntity getById(int idCar);
    public List<CarEntity> getByIdService(int ServiceId);
    public CarEntity getByIdShift(int ShiftId);
    public void addCar(CarEntity CarEntity);
    public void updateCar(CarEntity carEntity);
    public void deleteCar(CarEntity CarEntity);
    public List<CarEntity> getFreeCarsByTimeLimits(Timestamp timeIn, Timestamp timeOut);
}
