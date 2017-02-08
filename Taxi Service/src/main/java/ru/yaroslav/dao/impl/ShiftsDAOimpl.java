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
import ru.yaroslav.Entities.ShiftsEntity;
import ru.yaroslav.dao.interfaces.DispatcherDAO;
import ru.yaroslav.dao.interfaces.DriverDAO;
import ru.yaroslav.dao.interfaces.ShiftsDAO;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Yaroslav on 06.01.2017.
 */
@Repository
public class ShiftsDAOimpl implements ShiftsDAO {
    @Autowired
    SessionFactory sessionFactory;
   @Autowired
    DriverDAO driverDAO;
   @Autowired
    DispatcherDAO dispatcherDAO;

    Log logger = LogFactory.getLog(getClass());


    @Override
    public List<ShiftsEntity> getShiftsByDay(Timestamp timestamp) {
        Session session=sessionFactory.getCurrentSession();
        if(session.getTransaction()==null)
        session.beginTransaction();
        List<ShiftsEntity> shifts = session.createQuery("FROM ShiftsEntity " +
                "WHERE day(timeIn)=day('"+timestamp+"')").list();
        session.getTransaction().commit();
        session.close();
        return shifts;
    }

    @Override
    public List<ShiftsEntity> getShiftByDriverId(int worker_id) {
        Session session=sessionFactory.openSession();
        if(!session.getTransaction().isActive())session.beginTransaction();
        List<ShiftsEntity> shiftsEntities=session.createQuery("from ShiftsEntity where driverEntity.idWorker ="+worker_id).list();
        session.getTransaction().commit();
        session.close();
        return shiftsEntities;
    }


    @Override
    public List<ShiftsEntity> getShiftsByServiceId(int ServiceId) {
        Session session=sessionFactory.getCurrentSession();
        if(!session.getTransaction().isActive())
        session.beginTransaction();
        List<ShiftsEntity> shifts=session.createQuery("from ShiftsEntity  where driverEntity " +
                "in (from DriverEntity where taxiServiseEntity.idService="+ServiceId+") order by timeIn desc").list();
        return shifts;
    }

    @Override
    public ShiftsEntity getById(int id) {
        Session session=sessionFactory.getCurrentSession();
        if(!session.getTransaction().isActive()) session.beginTransaction();
        List<ShiftsEntity> shift=session.createQuery("FROM ShiftsEntity WHERE idShift = "+id).list();
        session.getTransaction().commit();
        session.close();
        return shift.get(0);
    }

    @Override
    public void update(ShiftsEntity shiftsEntity) {
        logger.info("Updating shift:");
        logger.info("driver= "+shiftsEntity.getDriverEntity().getFullName());
        logger.info("time limits: from "+shiftsEntity.getTimeIn()+" to "+shiftsEntity.getTimeOut());
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
       // shiftsEntity.setDriverEntity(driverEntity);
        session.createQuery("update from ShiftsEntity set timeIn='"+shiftsEntity.getTimeIn()+"'," +
                "timeOut='"+shiftsEntity.getTimeOut() +"' , driverEntity.idWorker="+shiftsEntity.getDriverEntity().getIdWorker()+" where idShift="+shiftsEntity.getIdShift()).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean isEditingCarNeeded(ShiftsEntity shiftsEntity) {
        List<ShiftsEntity> shiftsEntities=getShiftByCarId(shiftsEntity.getDriverEntity().getIdWorker());
        for (int i = 0; i < shiftsEntities.size(); i++) {
            if(shiftsEntities.get(i).getIdShift()==shiftsEntity.getIdShift()){
                shiftsEntities.remove(shiftsEntities.get(i));
            }
        }
        int counter=0;
        for (int j = 0; j < shiftsEntities.size(); j++) {
            if((shiftsEntities.get(j).getTimeOut().before(shiftsEntity.getTimeIn()) ||
                    shiftsEntity.getTimeOut().before(shiftsEntities.get(j).getTimeIn()))){
                counter++;
            }
        }
        if(counter==shiftsEntities.size()) return false;
        else return true;
    }

    @Override
    public boolean isEditingDriverNeeded( ShiftsEntity shiftsEntity) {
        List<ShiftsEntity> shiftsEntities=getShiftByDriverId(shiftsEntity.getDriverEntity().getIdWorker());
        for (int i = 0; i < shiftsEntities.size(); i++) {
            if(shiftsEntities.get(i).getIdShift()==shiftsEntity.getIdShift()){
                shiftsEntities.remove(shiftsEntities.get(i));
            }
        }
        int counter=0;
        for (int j = 0; j < shiftsEntities.size(); j++) {
            if((shiftsEntities.get(j).getTimeOut().before(shiftsEntity.getTimeIn()) ||
                    shiftsEntity.getTimeOut().before(shiftsEntities.get(j).getTimeIn()))){
                    counter++;
            }
        }
        if(counter==shiftsEntities.size()) return false;
        else return true;
    }

    @Override
    public void addShift(ShiftsEntity shiftsEntity) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(shiftsEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteShift(ShiftsEntity shiftsEntity) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        logger.info(shiftsEntity.getIdShift());
        session.remove(shiftsEntity);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<ShiftsEntity> getShiftByCarId(int car_id) {
        Session session=sessionFactory.openSession();
        if(!session.getTransaction().isActive())session.beginTransaction();
        List<ShiftsEntity> shiftsEntities=session.createQuery("from ShiftsEntity where carEntity.id_car ="+car_id).list();
        session.getTransaction().commit();
        session.close();
        return shiftsEntities;
    }

    @Override
    public List<ShiftsEntity> getShiftByTime(Timestamp time) {
        Session session=sessionFactory.openSession();
        if(!session.getTransaction().isActive())session.beginTransaction();
        List<ShiftsEntity> shiftsEntities=session.createQuery("from ShiftsEntity " +
                "where timeIn<'"+time+"' and timeOut>'"+time+"'").list();
        session.getTransaction().commit();
        session.close();
        return shiftsEntities;
    }
}
