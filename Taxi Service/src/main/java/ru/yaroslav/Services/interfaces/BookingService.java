package ru.yaroslav.Services.interfaces;

import ru.yaroslav.Entities.BookingEntity;

import java.util.List;

/**
 * Created by Yaroslav on 07.01.2017.
 */
public interface BookingService {
    public List<BookingEntity> getAllByServiceId(int ServiceId);
    public List<BookingEntity> getAllByShiftId(int ShiftId);
    public BookingEntity getById(int id);
    public void add(BookingEntity bookingEntity);
    public void delete(BookingEntity bookingEntity);
}

