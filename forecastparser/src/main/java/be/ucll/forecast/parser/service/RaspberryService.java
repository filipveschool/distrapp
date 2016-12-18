package be.ucll.forecast.parser.service;

import be.ucll.forecast.domain.HumidityRasp;
import be.ucll.forecast.domain.TemperatureRasp;

/**
 * Created by filip on 18/12/2016.
 */
public interface RaspberryService {
    TemperatureRasp getTemperature();

    HumidityRasp getHumidity();
}