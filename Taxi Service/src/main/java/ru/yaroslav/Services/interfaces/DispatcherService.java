package ru.yaroslav.Services.interfaces;

import ru.yaroslav.Entities.DispatcherEntity;

import java.util.List;

/**
 * Created by Yaroslav on 05.01.2017.
 */
public interface DispatcherService {
    public DispatcherEntity getDispatcherByFIO(String fio);
    public List<DispatcherEntity> getByIdService(int SriveceId);
    public void addDispatcher(DispatcherEntity dispatcherEntity);
    public void updateDispatcher(DispatcherEntity dispatcherEntity);
    public void deleteDispatcher(DispatcherEntity dispatcherEntity);
    public List<DispatcherEntity> getByPersonalCode(String code);
}
