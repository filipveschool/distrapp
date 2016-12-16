package be.ucll.forecastJPA.dao;

import be.ucll.forecast.domain.TemperatureRasp;
import be.ucll.forecastJPA.exception.DBException;

import javax.ejb.Remote;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by filipve on 16/12/2016.
 */
@Remote
public interface TemperatureRaspDB {

    TemperatureRasp getById( Integer id );

    TemperatureRasp getByDateTime( LocalDateTime dateTime ) throws DBException;

    void addTemperature( double temperatureData );

    void updateTemperature( TemperatureRasp temperatureRasp );

    void removeById( Integer id );

    List<TemperatureRasp> getAllTemperatures() throws DBException;
}
