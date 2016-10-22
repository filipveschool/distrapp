package be.ucll.forecast.dataService.repository;

import be.ucll.forecast.dataService.domain.Forecast;

import javax.ejb.EJB;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tompl on 10/18/2016.
 */

@EJB
public class ForecastRepository {

    Map<String, List<Forecast>> forecasts;

    public ForecastRepository(){
        forecasts = new HashMap<String, List<Forecast>>();
    }

    public List<Forecast> getForecast(String location) {
        return forecasts.get(location);
    }
}
