package be.ucll.forecast.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tompl on 10/11/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature {

    @JsonProperty
    private double day, min, max;

    public  Temperature(){
    }

    public double getDay() {
        return day;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
