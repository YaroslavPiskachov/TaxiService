package ru.yaroslav.Services.interfaces;

import ru.yaroslav.Entities.TaxiServiseEntity;

import java.util.List;

/**
 * Created by Yaroslav on 04.01.2017.
 */
public interface TaxiService_Service {
    public List<TaxiServiseEntity> getAll();
    public void addService(TaxiServiseEntity taxiServiseEntity);

}
