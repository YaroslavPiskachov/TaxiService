package ru.yaroslav.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yaroslav.Entities.DispatcherEntity;
import ru.yaroslav.Services.interfaces.DispatcherService;
import ru.yaroslav.dao.interfaces.DispatcherDAO;

import java.util.List;

/**
 * Created by Yaroslav on 05.01.2017.
 */
@Service
public class Dispatcher_ServiceImpl implements DispatcherService {

    @Autowired
    private DispatcherDAO dispatcherDAO;

    @Override
    public DispatcherEntity getDispatcherByFIO(String fio) {
        return dispatcherDAO.getDispatcherByFIO(fio);
    }

    @Override
    public void addDispatcher(DispatcherEntity dispatcherEntity) {
        dispatcherDAO.addDispatcher(dispatcherEntity);
    }

    @Override
    public void updateDispatcher(DispatcherEntity dispatcherEntity) {
        dispatcherDAO.updateDispatcher(dispatcherEntity);
    }

    @Override
    public void deleteDispatcher(DispatcherEntity dispatcherEntity) {
        dispatcherDAO.deleteDispatcher(dispatcherEntity);
    }

    @Override
    public List<DispatcherEntity> getByPersonalCode(String code) {
        return dispatcherDAO.getByPersonalCode(code);
    }

    @Override
    public List<DispatcherEntity> getByIdService(int SriveceId) {
        return dispatcherDAO.getByIdService(SriveceId);
    }
}
