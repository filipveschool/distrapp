package be.ucll.forecast.api.service;


import be.ucll.forecast.domain.Forecast;

import javax.ejb.Local;
import javax.ejb.Remote;

/**
 * Created by tompl on 11/11/2016.
 */

public interface ApiService {
    Forecast getForecast(String location);
}
