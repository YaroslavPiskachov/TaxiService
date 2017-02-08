package ru.yaroslav.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import ru.yaroslav.Entities.BookingEntity;
import ru.yaroslav.Entities.DispatcherEntity;
import ru.yaroslav.Entities.DriverEntity;
import ru.yaroslav.Entities.ShiftsEntity;
import ru.yaroslav.Services.interfaces.BookingService;
import ru.yaroslav.Services.interfaces.DispatcherService;
import ru.yaroslav.Services.interfaces.ShiftsService;
import ru.yaroslav.dao.interfaces.DriverDAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaroslav on 06.01.2017.
 */
@Repository
public class DriverDAOimpl implements DriverDAO {
    Log logger = LogFactory.getLog(getClass());
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    ShiftsService shiftsService;
    @Autowired
    BookingService bookingService;
    @Autowired
    DispatcherService service;
    @Override
    public DriverEntity getById(int id) {
        Session session=sessionFactory.getCurrentSession();
        if(session.getTransaction().isActive()==false)
        session.beginTransaction();
        List<DriverEntity> driverEntity= session.createQuery("FROM DriverEntity WHERE idWorker = "+id).list();
        session.getTransaction().commit();
        session.close();
        return driverEntity.get(0);
    }

    @Override
    public void deleteDriver(DriverEntity driverEntity) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        logger.info(driverEntity.getIdWorker());
        session.remove(driverEntity);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateDriver(DriverEntity driverEntity) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        ///session.update(driverEntity);
        session.createQuery("update from DriverEntity set fullName='"+driverEntity.getFullName()+"'," +
                "expiriance="+driverEntity.getExpiriance()+", mobNumber='"+driverEntity.getMobNumber()+"'" +
                "where idWorker="+driverEntity.getIdWorker()).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void addDriver(DriverEntity driverEntity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DispatcherEntity dispatcherEntity=service.getDispatcherByFIO(auth.getName());
        driverEntity.setTaxiServiseEntity(dispatcherEntity.getTaxiServiseEntity());
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(driverEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public DriverEntity getDriverByIdBoking(int id) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        BookingEntity bookingEntity=bookingService.getById(id);
        DriverEntity driverEntity=bookingEntity.getShiftsEntity().getDriverEntity();
        session.getTransaction().commit();
        session.close();
        return driverEntity;
    }

    @Override
    public List<DriverEntity> getByIdService(int ServiceId) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<DriverEntity> drivers=session.createQuery("FROM DriverEntity WHERE taxiServiseEntity.idService="+ServiceId).list();
        session.getTransaction().commit();
        session.close();
        return drivers;
    }

    @Override
    @SuppressWarnings("unchecked")
    public DriverEntity getDriverByIdShift(int id) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        ShiftsEntity shift=shiftsService.getById(id);
        DriverEntity driver= shift.getDriverEntity();
        session.getTransaction().commit();
        session.close();
        return driver;
    }

    @Override
    public List<DriverEntity> getFreeDriversByTimeLimits(Timestamp ShiftTimeIn, Timestamp ShiftTimeOut) {


        List<DriverEntity> ResultDriverEntities= new ArrayList<DriverEntity>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DispatcherEntity dispatcherEntity=service.getDispatcherByFIO(auth.getName());
        List<DriverEntity> allDrivers=getByIdService(dispatcherEntity.getTaxiServiseEntity().getIdService());

        Timestamp in=new Timestamp(ShiftTimeIn.getTime());
        Timestamp out=new Timestamp(ShiftTimeOut.getTime());
        in.setHours(in.getHours()-15);
        out.setHours(out.getHours()+15);


        for (int i = 0; i < allDrivers.size(); i++) {
            List<ShiftsEntity> shiftsEntities=shiftsService.getShiftByDriverId(allDrivers.get(i).getIdWorker());
            int t=0;
            for (int j = 0; j < shiftsEntities.size(); j++) {
                if((shiftsEntities.get(j).getTimeOut().before(in) ||
                        out.before(shiftsEntities.get(j).getTimeIn()))){

                    t++;
                }
            }
            if(t==shiftsEntities.size())ResultDriverEntities.add(allDrivers.get(i));
        }

        return ResultDriverEntities;
    }
}
