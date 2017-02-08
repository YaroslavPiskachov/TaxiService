package ru.yaroslav.Services.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yaroslav.Entities.TaxiServiseEntity;
import ru.yaroslav.Services.interfaces.TaxiService_Service;
import ru.yaroslav.dao.impl.TaxiServiceDAOimpl;
import ru.yaroslav.dao.interfaces.TaxiServiceDAO;

import java.util.List;

/**
 * Created by Yaroslav on 04.01.2017.
 */


@Service
public class TaxiService_ServiceImpl implements TaxiService_Service {


    @Autowired
    private TaxiServiceDAO taxiServiceDAO;

    @Transactional
    public List<TaxiServiseEntity> getAll(){
        return taxiServiceDAO.getAll();
    }


    @Transactional
    public void addService(TaxiServiseEntity taxiServiseEntity){
        taxiServiceDAO.addService(taxiServiseEntity);
    }
}
