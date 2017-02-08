package ru.yaroslav.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import ru.yaroslav.Entities.CarEntity;
import ru.yaroslav.Entities.DispatcherEntity;
import ru.yaroslav.Entities.DriverEntity;
import ru.yaroslav.Entities.ShiftsEntity;
import ru.yaroslav.Services.interfaces.BookingService;
import ru.yaroslav.Services.interfaces.DispatcherService;
import ru.yaroslav.Services.interfaces.ShiftsService;
import ru.yaroslav.dao.interfaces.CarDAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yaroslav on 19.01.2017.
 */
@Repository
public class CarDAOimpl implements CarDAO{
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
    public CarEntity getById(int idCar) {
        Session session=sessionFactory.getCurrentSession();
        if(session.getTransaction().isActive()==false) session.beginTransaction();
        List<CarEntity> carEntity= session.createQuery("FROM CarEntity WHERE id_car = "+idCar).list();
        session.getTransaction().commit();
        session.close();
        return carEntity.get(0);
    }

    @Override
    public List<CarEntity> getByIdService(int ServiceId) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<CarEntity> cars=session.createQuery("FROM CarEntity WHERE taxiServiseEntity.idService="+ServiceId).list();
        session.getTransaction().commit();
        session.close();
        return cars;
    }

    @Override
    public CarEntity getByIdShift(int ShiftId) {
        ShiftsEntity shift=shiftsService.getById(ShiftId);
        CarEntity car= shift.getCarEntity();
        return car;
    }

    @Override
    public void addCar(CarEntity CarEntity) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DispatcherEntity dispatcherEntity=service.getDispatcherByFIO(auth.getName());
        CarEntity.setTaxiServiseEntity(dispatcherEntity.getTaxiServiseEntity());
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(CarEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateCar(CarEntity carEntity) {
        logger.info("car model: "+carEntity.getModel());
        logger.info("car number: "+carEntity.getNumberOfCar());
        logger.info("car year: "+carEntity.getYear());
        logger.info("car id: "+carEntity.getId_car());
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        //session.update(carEntity);
        session.createQuery("update from CarEntity set model='"+carEntity.getModel()+"'," +
                "year='"+carEntity.getYear()+"', numberOfCar='"+carEntity.getNumberOfCar()+"'," +
                "gasolineRate=" +carEntity.getGasolineRate()+
                "where id_car="+carEntity.getId_car()).executeUpdate();
//        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteCar(CarEntity CarEntity) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        logger.info(CarEntity.getId_car());
        session.remove(CarEntity);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<CarEntity> getFreeCarsByTimeLimits(Timestamp ShiftTimeIn, Timestamp ShiftTimeOut) {
        List<CarEntity> ResultCarEntities= new ArrayList<CarEntity>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DispatcherEntity dispatcherEntity=service.getDispatcherByFIO(auth.getName());
        List<CarEntity> allCars=getByIdService(dispatcherEntity.getTaxiServiseEntity().getIdService());

        Timestamp in=new Timestamp(ShiftTimeIn.getTime());
        Timestamp out=new Timestamp(ShiftTimeOut.getTime());


        for (int i = 0; i < allCars.size(); i++) {
            List<ShiftsEntity> shiftsEntities=shiftsService.getShiftByCarId(allCars.get(i).getId_car());
            int t=0;
            for (int j = 0; j < shiftsEntities.size(); j++) {
                if((shiftsEntities.get(j).getTimeOut().before(in) ||
                        out.before(shiftsEntities.get(j).getTimeIn()))){

                    t++;
                }
            }
            if(t==shiftsEntities.size())ResultCarEntities.add(allCars.get(i));
        }

        return ResultCarEntities;
    }
}
