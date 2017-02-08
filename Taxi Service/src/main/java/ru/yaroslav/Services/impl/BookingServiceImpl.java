package ru.yaroslav.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yaroslav.Entities.BookingEntity;
import ru.yaroslav.Services.interfaces.BookingService;
import ru.yaroslav.dao.interfaces.BookingDAO;

import java.util.List;

/**
 * Created by Yaroslav on 07.01.2017.
 */
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingDAO bookingDAO;
    @Override
    public List<BookingEntity> getAllByServiceId(int ServiceId) {

        return bookingDAO.getAllByServiceId(ServiceId);
    }

    @Override
    public List<BookingEntity> getAllByShiftId(int ShiftId) {
        return bookingDAO.getAllByShiftId(ShiftId);
    }

    @Override
    public BookingEntity getById(int id) {
        return bookingDAO.getById(id);
    }

    @Override
    public void add(BookingEntity bookingEntity) {
        bookingDAO.add(bookingEntity);
    }

    @Override
    public void delete(BookingEntity bookingEntity) {
        bookingDAO.delete(bookingEntity);
    }
}
