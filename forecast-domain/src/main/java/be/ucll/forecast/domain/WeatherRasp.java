package be.ucll.forecast.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by filip on 18/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherRasp {

    @JsonProperty("humidity") //Dit is de waarde van het JSON bestand dat van de raspberry pi komt.
    private double humidityData;

    @JsonProperty("temp") //Dit is de waarde van het JSON bestand dat van de raspberry pi komt.
    private double temperatureData;

    public WeatherRasp() {

    }

    public double getHumidityData() {
        return humidityData;
    }

    public void setHumidityData(double humidityData) {
        this.humidityData = humidityData;
    }

    public double getTemperatureData() {
        return temperatureData;
    }

    public void setTemperatureData(double temperatureData) {
        this.temperatureData = temperatureData;
    }

    @Override
    public String toString() {
        return "WeatherRasp{" +
                "humidityData=" + humidityData +
                ", temperatureData=" + temperatureData +
                '}';
    }
}
