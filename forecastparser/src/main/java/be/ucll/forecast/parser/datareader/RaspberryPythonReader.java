package be.ucll.forecast.parser.datareader;

import be.ucll.forecast.domain.WeatherRasp;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

/**
 * Created by filip on 18/12/2016.
 */
@Stateless
@Alternative //TODO alternative op het einde wegdoen en de vaste reader van maken
public class RaspberryPythonReader implements ReaderFactory {

    //Dit is het path zoals in de opgave staat voor de weerdata op te halen op de raspberry PI.
    private static final String RASPBERRY_PYTHON_PATH = "/projects/readDHT22.py";

    @Override
    public WeatherRasp readWeatherData() {
        try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("python /projects/readDHT22.py 2302 22");
            Process pr = rt.exec("python " + RASPBERRY_PYTHON_PATH + " 2302 22");
            return new ObjectMapper().readValue(pr.getOutputStream().toString(), WeatherRasp.class);
        } catch (Exception e) {
            return null;
        }
    }
}


