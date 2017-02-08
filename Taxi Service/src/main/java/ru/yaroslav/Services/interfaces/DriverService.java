package ru.yaroslav.Services.interfaces;

import ru.yaroslav.Entities.DriverEntity;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Yaroslav on 08.01.2017.
 */
public interface DriverService {
    public DriverEntity getById(int id);
    public List<DriverEntity> getByIdService(int ServiceId);
    public void addDriver(DriverEntity driverEntity);
    public void updateDriver(DriverEntity driverEntity);
    public void deleteDriver(DriverEntity driverEntity);
    public List<DriverEntity> getFreeDriversByTimeLimits(Timestamp timeIn, Timestamp timeOut);

}
