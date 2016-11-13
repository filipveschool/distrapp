package service;

import be.ucll.forecast.domain.Forecast;
import be.ucll.forecast.domain.Observation;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by tompl on 11/12/2016.
 */
public class OpenWeatherServiceImplementation implements OpenWeatherService {

    @Override
    public Observation getObservation(String location) {
        Client client = ClientBuilder.newClient();
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+location+"&APPID=31c73e938fba1a40663c14da4acd8325&units=metric";
        WebTarget target = client.target(url);
        Observation observation = target.request(MediaType.APPLICATION_JSON).get(Observation.class);
        return observation;
    }

    @Override
    public Forecast getForecast(String location) {
        Client client = ClientBuilder.newClient();
        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+location+"&APPID=31c73e938fba1a40663c14da4acd8325&units=metric";
        WebTarget target = client.target(url);
        Forecast forecast = target.request(MediaType.APPLICATION_JSON).get(Forecast.class);
        return forecast;
    }
}
