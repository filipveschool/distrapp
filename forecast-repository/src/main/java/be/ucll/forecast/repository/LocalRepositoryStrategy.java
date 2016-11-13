package be.ucll.forecast.repository;

import be.ucll.forecast.domain.Forecast;
import be.ucll.forecast.domain.Observation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tompl on 11/12/2016.
 */

public class LocalRepositoryStrategy implements Repository {

    private Map<String, Forecast> forecasts;

    public LocalRepositoryStrategy(){
        forecasts = new HashMap<>();
    }

    @Override
    public void save(Forecast forecast) {
        forecasts.put(forecast.getCity().getName() , forecast);
    }

    @Override
    public Observation find(String name, String date) {
        if (forecasts.containsKey(name)){
            Forecast forecast = forecasts.get(name);
            List<Observation> observations = forecast.getList();

            for(Observation observation : observations){
                if (observation.getDt().equals(date)){
                    return observation;
                }
            }
        }
        return null;
    }

    @Override
    public Forecast findAll(String name) {
        if (forecasts.containsKey(name)){
            return forecasts.get(name);
        }
        return null;
    }

    @Override
    public boolean exists(String date) {
        Set<String> keys = forecasts.keySet();
        if( keys.contains(date) )
            return true;
        else
            return false;
    }
}
