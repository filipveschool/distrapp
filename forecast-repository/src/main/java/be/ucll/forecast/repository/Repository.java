package be.ucll.forecast.repository;

import be.ucll.forecast.domain.Forecast;
import be.ucll.forecast.domain.Observation;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;


/**
 * Created by tompl on 10/18/2016.
 */

public interface Repository {
    void save(Forecast forecast);
    Observation find(String name, String date);
    Forecast findAll(String name);
    boolean exists(String date);
}
