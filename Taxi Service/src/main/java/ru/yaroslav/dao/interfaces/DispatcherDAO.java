package ru.yaroslav.dao.interfaces;

import ru.yaroslav.Entities.DispatcherEntity;
import ru.yaroslav.Entities.DriverEntity;

import java.util.List;

/**
 * Created by Yaroslav on 05.01.2017.
 */
public interface DispatcherDAO {
    public DispatcherEntity getDispatcherByFIO(String fio);
    public List<DispatcherEntity> getByIdService(int SriveceId);
    public void addDispatcher(DispatcherEntity dispatcherEntity);
    public void updateDispatcher(DispatcherEntity dispatcherEntity);
    public void deleteDispatcher(DispatcherEntity dispatcherEntity);
    public List<DispatcherEntity> getByPersonalCode(String code);
}
