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

    List<HumidityRasp> getHumiditysBeforeDate(LocalDateTime dateTime);

    List<HumidityRasp> getHumiditysAfterDayMonthYearDate(Integer day, Integer month, Integer year);

    List<HumidityRasp> getHumiditysAfterMonthYearDate(Integer month, Integer year);

    List<HumidityRasp> getHumiditysAfterDayMonthDate(Integer day, Integer month);

    List<HumidityRasp> getHumiditysAfterDayDate(Integer day);

    List<HumidityRasp> getHumiditysOfLocalDateTime(LocalDateTime localDateTime);

    List<HumidityRasp> getHumiditysOfDayAndMonth(Integer monthvalue, Integer dayvalue);

    List<HumidityRasp> getHumiditysOfMonth(Integer monthvalue);

}
