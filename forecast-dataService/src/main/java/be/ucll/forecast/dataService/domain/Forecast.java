package be.ucll.forecast.dataService.domain;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tompl on 9/27/2016.
 */

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {

    private String location;
    private Date forecastDate;


    private Calendar time;

    public Forecast(){

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
        this.forecastDate = forecastDate;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

}
