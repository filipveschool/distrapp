package be.ucll.forecast.api.service;

import be.ucll.forecast.api.domain.Observation;

/**
 * Created by tomploem on 9/27/2016.
 */

//@Stateless
public class ForecastApiServiceImpl implements ForecastApiService {

    public Observation getCurrentObservation(String location) {
        //Client client = ClientBuilder.newClient();
       //String c = "http://api.openweathermap.org/data/2.5/weather?q="+location+"&APPID=31c73e938fba1a40663c14da4acd8325&units=metric";
        //WebTarget target = client.target(c);
        //Observation observation = target.request(MediaType.APPLICATION_JSON).get(Observation.class);
        return null;//observation;
    }

}
