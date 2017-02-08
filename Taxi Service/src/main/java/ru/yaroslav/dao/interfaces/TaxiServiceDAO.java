package ru.yaroslav.dao.interfaces;

import ru.yaroslav.Entities.TaxiServiseEntity;

import java.util.List;

/**
 * Created by Yaroslav on 29.12.2016.
 */
public interface TaxiServiceDAO {
    public List<TaxiServiseEntity> getAll();
    public void addService(TaxiServiseEntity taxiServiseEntity);
}
