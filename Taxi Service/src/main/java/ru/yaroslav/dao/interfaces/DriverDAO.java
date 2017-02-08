package ru.yaroslav.dao.interfaces;

import ru.yaroslav.Entities.DriverEntity;
import ru.yaroslav.Entities.ShiftsEntity;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Yaroslav on 06.01.2017.
 */
public interface DriverDAO {
    public DriverEntity getById(int id);
    public List<DriverEntity> getByIdService(int ServiceId);
    public DriverEntity getDriverByIdShift(int id);
    public DriverEntity getDriverByIdBoking(int id);
    public void addDriver(DriverEntity driverEntity);
    public void updateDriver(DriverEntity driverEntity);
    public void deleteDriver(DriverEntity driverEntity);
    public List<DriverEntity> getFreeDriversByTimeLimits(Timestamp timeIn,Timestamp timeOut);


}
