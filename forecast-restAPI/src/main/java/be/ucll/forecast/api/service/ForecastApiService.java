package be.ucll.forecast.api.service;

import be.ucll.forecast.api.domain.Observation;

/**
 * Created by tompl on 9/27/2016.
 */

//@Remote
public interface ForecastApiService {
    Observation getCurrentObservation(String location);
}
