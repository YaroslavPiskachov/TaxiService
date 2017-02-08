package ru.yaroslav.dao.interfaces;

import ru.yaroslav.Entities.BookingEntity;
import ru.yaroslav.Entities.DriverEntity;

import java.util.List;

/**
 * Created by Yaroslav on 06.01.2017.
 */
public interface BookingDAO {
    public List<BookingEntity> getAllByServiceId(int ServiceId);
    public List<BookingEntity> getAllByShiftId(int ShiftId);
    public BookingEntity getById(int id);
    public void add(BookingEntity bookingEntity);
    public void delete(BookingEntity bookingEntity);
}
