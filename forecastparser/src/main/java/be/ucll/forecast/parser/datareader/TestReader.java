package be.ucll.forecast.parser.datareader;

import be.ucll.forecast.domain.WeatherRasp;

import javax.ejb.Stateless;

/**
 * Created by filip on 18/12/2016.
 */
@Stateless
public class TestReader implements ReaderFactory {
    @Override
    public WeatherRasp readWeatherData() {
        WeatherRasp testObject = new WeatherRasp();
        testObject.setHumidityData(52.5); // Deze waarde komt uit het opgavevoorbeeld
        testObject.setTemperatureData(18.5); // Deze waarde komt uit het opgavevoorbeeld
        return testObject;
    }
}
