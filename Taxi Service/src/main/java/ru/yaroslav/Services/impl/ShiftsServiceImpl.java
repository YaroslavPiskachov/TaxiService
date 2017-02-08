package ru.yaroslav.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yaroslav.Entities.DriverEntity;
import ru.yaroslav.Entities.ShiftsEntity;
import ru.yaroslav.Services.interfaces.ShiftsService;
import ru.yaroslav.dao.interfaces.ShiftsDAO;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Yaroslav on 09.01.2017.
 */
@Service
public class ShiftsServiceImpl implements ShiftsService {
    @Autowired
    ShiftsDAO shiftsDAO;

    @Override
    public List<ShiftsEntity> getShiftsByDay(Timestamp timestamp) {
        return shiftsDAO.getShiftsByDay(timestamp);
    }

    @Override
    public ShiftsEntity getById(int id) {
        return shiftsDAO.getById(id);
    }

    @Override
    public List<ShiftsEntity> getShiftsByServiceId(int ServiceId) {
        return shiftsDAO.getShiftsByServiceId(ServiceId);
    }

    @Override
    public void addShift(ShiftsEntity shiftsEntity) {
        shiftsDAO.addShift(shiftsEntity);
    }

    @Override
    public List<ShiftsEntity> getShiftByDriverId(int worker_id) {
        return shiftsDAO.getShiftByDriverId(worker_id);
    }

    @Override
    public void update(ShiftsEntity shiftsEntity) {
        shiftsDAO.update(shiftsEntity);
    }

    @Override
    public void deleteShift(ShiftsEntity shiftsEntity) {
        shiftsDAO.deleteShift(shiftsEntity);
    }

    @Override
    public boolean isEditingDriverNeeded(ShiftsEntity shiftsEntity) {
        return shiftsDAO.isEditingDriverNeeded(shiftsEntity);
    }

    @Override
    public List<ShiftsEntity> getShiftByCarId(int car_id) {
        return shiftsDAO.getShiftByCarId(car_id);
    }

    @Override
    public boolean isEditingCarNeeded(ShiftsEntity shiftsEntity) {
        return shiftsDAO.isEditingCarNeeded(shiftsEntity);
    }

    @Override
    public List<ShiftsEntity> getShiftByTime(Timestamp time) {
        return shiftsDAO.getShiftByTime(time);
    }
}
