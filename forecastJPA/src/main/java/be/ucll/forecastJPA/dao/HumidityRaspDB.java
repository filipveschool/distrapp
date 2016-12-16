package be.ucll.forecastJPA.dao;

import be.ucll.forecast.domain.HumidityRasp;
import be.ucll.forecastJPA.exception.DBException;

import javax.ejb.Remote;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by filipve on 16/12/2016.
 */
@Remote
public interface HumidityRaspDB {

    HumidityRasp getById( Integer id );

    HumidityRasp getByDateTime( LocalDateTime dateTime ) throws DBException;

    void addHumidity( double humidityData );

    void updateHumidity( HumidityRasp humidityRasp );

    void removeById( Integer id );

    List<HumidityRasp> getAllHumiditys() throws DBException;

}
