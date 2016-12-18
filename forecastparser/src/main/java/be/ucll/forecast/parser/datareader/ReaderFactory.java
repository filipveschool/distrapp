package be.ucll.forecast.parser.datareader;

import be.ucll.forecast.domain.WeatherRasp;

/**
 * Created by filip on 18/12/2016.
 */
public interface ReaderFactory {

    WeatherRasp readWeatherData();
}
