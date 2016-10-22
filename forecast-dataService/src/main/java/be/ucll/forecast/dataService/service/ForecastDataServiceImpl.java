package be.ucll.forecast.dataService.service;

import be.ucll.forecast.dataService.domain.Forecast;
import be.ucll.forecast.dataService.repository.ForecastRepository;

import java.util.List;

/**
 * Created by tompl on 10/18/2016.
 */

//@Default
public class ForecastDataServiceImpl implements ForecastDataService {

    //@EJB
    ForecastRepository forecastRepository;

    public List<Forecast> getForecast(String location) {
        return forecastRepository.getForecast(location);
    }
}
