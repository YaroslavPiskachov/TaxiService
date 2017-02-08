package ru.yaroslav.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yaroslav.Entities.CarEntity;
import ru.yaroslav.Services.interfaces.CarService;
import ru.yaroslav.dao.interfaces.CarDAO;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Yaroslav on 19.01.2017.
 */
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarDAO carDAO;


    @Override
    public CarEntity getById(int idCar) {
        return carDAO.getById(idCar);
    }

    @Override
    public List<CarEntity> getByIdService(int ServiceId) {
        return carDAO.getByIdService(ServiceId);
    }

    @Override
    public void addCar(CarEntity CarEntity) {
        carDAO.addCar(CarEntity);
    }

    @Override
    public void updateCar(CarEntity carEntity) {
        carDAO.updateCar(carEntity);
    }

    @Override
    public void deleteCar(CarEntity CarEntity) {
        carDAO.deleteCar(CarEntity);
    }

    @Override
    public List<CarEntity> getFreeCarsByTimeLimits(Timestamp timeIn, Timestamp timeOut) {
        return carDAO.getFreeCarsByTimeLimits(timeIn,timeOut);
    }

    @Override
    public CarEntity getByIdShift(int ShiftId) {
        return carDAO.getByIdShift(ShiftId);
    }
}
