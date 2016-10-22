package be.ucll.forecast.service;

import be.ucll.forecast.domain.Observation;

import javax.ejb.Local;

/**
 * Created by tompl on 9/27/2016.
 */

@Local
public interface ForecastApiService {
    Observation getCurrentObservation(String location);
}
