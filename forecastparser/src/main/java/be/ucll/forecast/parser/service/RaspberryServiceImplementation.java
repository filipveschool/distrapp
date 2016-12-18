package be.ucll.forecast.parser.service;

import be.ucll.forecast.domain.HumidityRasp;
import be.ucll.forecast.domain.TemperatureRasp;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Stateless;

/**
 * Created by filip on 18/12/2016.
 */
@Stateless
public class RaspberryServiceImplementation implements RaspberryService {

    @Override
    public TemperatureRasp getTemperature() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("python /projects/readDHT22.py 2302 22");
            return new ObjectMapper().readValue(pr.getOutputStream().toString(), TemperatureRasp.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public HumidityRasp getHumidity() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("python /projects/readDHT22.py 2302 22");
            return new ObjectMapper().readValue(pr.getOutputStream().toString(), HumidityRasp.class);
        } catch (Exception e) {
            return null;
        }
    }
}