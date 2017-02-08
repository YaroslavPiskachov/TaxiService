package ru.yaroslav.dao.impl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import ru.yaroslav.Entities.*;
import ru.yaroslav.dao.interfaces.BookingDAO;

import java.util.List;

/**
 * Created by Yaroslav on 06.01.2017.
 */
@Repository
public class BookingDAOimpl implements BookingDAO{
    @Autowired
    SessionFactory sessionFactory;
    Log logger = LogFactory.getLog(getClass());

    @Override
    public List<BookingEntity> getAllByShiftId(int ShiftId) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<BookingEntity> bookingEntities =session.createQuery("FROM BookingEntity WHERE shiftsEntity.idShift = "+ShiftId).list();
        session.getTransaction().commit();
        session.close();
        return bookingEntities;
    }

    @Override
    public BookingEntity getById(int id) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        BookingEntity bookEntity= (BookingEntity)session.createQuery("FROM BookingEntity WHERE idBooking = "+id);
        session.getTransaction().commit();
        session.close();
        return bookEntity;
    }

    @Override
    public List<BookingEntity> getAllByServiceId(int ServiceId) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<BookingEntity> bookingList = session.createQuery("from BookingEntity where shiftsEntity in " +
                "(from ShiftsEntity  where driverEntity.taxiServiseEntity.idService= "+ServiceId+") order by time").list();
        session.getTransaction().commit();
        session.close();
        return bookingList;
    }

    @Override
    public void add(BookingEntity bookingEntity) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(bookingEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(BookingEntity bookingEntity) {
        Session session=sessionFactory.getCurrentSession();
        session.beginTransaction();
        logger.info(bookingEntity.getIdBooking());
        session.remove(bookingEntity);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }
}
