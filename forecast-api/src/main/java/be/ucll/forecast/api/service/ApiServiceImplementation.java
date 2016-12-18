package be.ucll.forecast.api.service;


import be.ucll.forecast.domain.Forecast;
import be.ucll.forecast.domain.Observation;
import be.ucll.forecast.repository.LocalRepositoryStrategy;
import be.ucll.forecast.repository.Repository;
import service.OpenWeatherService;
import service.OpenWeatherServiceImplementation;


import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by tompl on 11/11/2016.
 */


@Stateless
public class ApiServiceImplementation implements ApiService {

    @EJB
    Repository repository;

    //Repository repository = new LocalRepositoryStrategy();
    OpenWeatherService openWeatherService = new OpenWeatherServiceImplementation();

    public Forecast getForecast(String location) {

        // Heeft repository een observation van vandaag?
        String dateForForecast = createDate(-3);

        // Is er een forecast van de voorbije dagen aanwezig?
        if (!repository.exists(dateForForecast)) {
            Forecast forecast = openWeatherService.getForecast(location);
            repository.save(forecast);
        }
        return repository.findAll(location);
    }

    private String createDate(int pastdays) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, pastdays);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(cal.getTime());
    }

    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    public void getForecastTimerSchedule() {
        //System.out.println("hoi allemaal timer is bezig in apiserviceimplementation klasse");
        Forecast forecast = getForecast("Leuven");
//        System.out.println("forecast van de stad : " + forecast.getCity().getName());
//        for (Observation observation : forecast.getList()) {
//            System.out.println("Datum observation : " + observation.getDt() +
//                    " - Dag" + observation.getTemp().getDay() +
//                    " - minimum temp: " + observation.getTemp().getMin()
//                    + " - maximum temp: " + observation.getTemp().getMax());
//        }
    }

}

