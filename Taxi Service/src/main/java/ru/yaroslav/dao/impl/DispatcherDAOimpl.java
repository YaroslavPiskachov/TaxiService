package ru.yaroslav.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import ru.yaroslav.Entities.DispatcherEntity;
import ru.yaroslav.Entities.DriverEntity;
import ru.yaroslav.dao.interfaces.DispatcherDAO;

import java.util.List;
import java.util.Random;

/**
 * Created by Yaroslav on 05.01.2017.
 */
@Repository
public class DispatcherDAOimpl implements DispatcherDAO {
    Log logger = LogFactory.getLog(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public DispatcherEntity getDispatcherByFIO(String fio) {
        Session session=sessionFactory.getCurrentSession();
        if(session.getTransaction().isActive()==false) session.beginTransaction();
        List<DispatcherEntity> dispatchers=session.createQuery("FROM DispatcherEntity WHERE fullName = '"+fio+"'").list();

        DispatcherEntity dispatcherEntity=null;
            dispatcherEntity=dispatchers.get(0);


        session.getTransaction().commit();
        session.close();
        return dispatcherEntity;
    }

    @Override
    public List<DispatcherEntity> getByIdService(int SriveceId) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<DispatcherEntity> dispatchers=session.createQuery("FROM DispatcherEntity WHERE taxiServiseEntity.idService="+SriveceId).list();
        session.getTransaction().commit();
        session.close();
        return dispatchers;
    }

    @Override
    public List<DispatcherEntity> getByPersonalCode(String code) {
        Session session=sessionFactory.getCurrentSession();
        if(session.getTransaction().isActive()==false) session.beginTransaction();
        List<DispatcherEntity> dispatchers=session.createQuery("FROM DispatcherEntity WHERE personalCode = '"+code+"'").list();
        session.getTransaction().commit();
        session.close();
        return dispatchers;
    }

    @Override
    public void addDispatcher(DispatcherEntity dispatcherEntity) {
        Random random=new Random();
        int b;
        do {
            b=random.nextInt(999999);
        }
        while(getByPersonalCode(String.valueOf(b)).size()!=0);
        dispatcherEntity.setPersonalCode(String.valueOf(b));
        if(dispatcherEntity.getTaxiServiseEntity()==null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            DispatcherEntity dispatcherEntity1 = getDispatcherByFIO(auth.getName());
            dispatcherEntity.setTaxiServiseEntity(dispatcherEntity1.getTaxiServiseEntity());
        }
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(dispatcherEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateDispatcher(DispatcherEntity dispatcherEntity) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("update from DispatcherEntity set fullName='"+dispatcherEntity.getFullName()+"'," +
        "age='"+dispatcherEntity.getAge()+"' where personalCode='"+dispatcherEntity.getPersonalCode()+"'").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteDispatcher(DispatcherEntity dispatcherEntity) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        logger.info(dispatcherEntity.getPersonalCode());
        session.remove(dispatcherEntity);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }
}
