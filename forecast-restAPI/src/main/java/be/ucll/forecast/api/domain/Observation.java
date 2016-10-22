package be.ucll.forecast.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;

/**
 * Created by tompl on 9/27/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Observation {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm", timezone = "Europe/Brussels")
    private Calendar time;

    private String name;

    @JsonProperty
    private Temperature main;

    public Observation() {
        setTime(Calendar.getInstance());
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
