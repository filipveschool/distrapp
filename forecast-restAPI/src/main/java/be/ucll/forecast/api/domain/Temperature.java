package be.ucll.forecast.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tompl on 10/11/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature {

    @JsonProperty
    private double temp, temp_min,temp_max;

    public  Temperature(){
    }
}
