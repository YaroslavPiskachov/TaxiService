package ru.yaroslav.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yaroslav.Entities.DriverEntity;
import ru.yaroslav.Services.interfaces.DriverService;
import ru.yaroslav.dao.interfaces.DriverDAO;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Yaroslav on 08.01.2017.
 */
@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    DriverDAO driverDAO;
    @Override
    public DriverEntity getById(int id) {
        return driverDAO.getById(id);
    }

    @Override
    public List<DriverEntity> getByIdService(int ServiceId) {
        return driverDAO.getByIdService(ServiceId);
    }

    @Override
    public void updateDriver(DriverEntity driverEntity) {
        driverDAO.updateDriver(driverEntity);
    }

    @Override
    public void deleteDriver(DriverEntity driverEntity) {
        driverDAO.deleteDriver(driverEntity);
    }

    @Override
    public void addDriver(DriverEntity driverEntity) {
        driverDAO.addDriver(driverEntity);
    }

    @Override
    public List<DriverEntity> getFreeDriversByTimeLimits(Timestamp timeIn, Timestamp timeOut) {
        return driverDAO.getFreeDriversByTimeLimits(timeIn,timeOut);
    }
}
