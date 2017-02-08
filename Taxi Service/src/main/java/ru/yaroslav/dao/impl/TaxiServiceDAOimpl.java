package ru.yaroslav.dao.impl;

import org.apache.commons.logging.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yaroslav.Entities.TaxiServiseEntity;
import ru.yaroslav.dao.interfaces.TaxiServiceDAO;

import org.hibernate.Query;
import java.util.List;

/**
 * Created by Yaroslav on 29.12.2016.
 */


@Repository
public class TaxiServiceDAOimpl implements TaxiServiceDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<TaxiServiseEntity> getAll() {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<TaxiServiseEntity> servicesList = session.createQuery("from TaxiServiseEntity ").list();
        session.getTransaction().commit();
        session.close();
        return servicesList;
    }

    @Override
    public void addService(TaxiServiseEntity taxiServiseEntity){
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(taxiServiseEntity);
        session.getTransaction().commit();
        session.close();
    }
}
