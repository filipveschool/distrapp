package service;

import be.ucll.forecast.domain.Forecast;
import be.ucll.forecast.domain.Observation;

/**
 * Created by tompl on 11/12/2016.
 */
public interface OpenWeatherService {
    Observation getObservation(String location);
    Forecast getForecast(String location);
}
