package be.ucll.forecast.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by tompl on 9/27/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Observation {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone = "Europe/Brussels")
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        format.format(time.getTime());
        this.time = format.getCalendar();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Temperature getMain() {
        return main;
    }

    public void setMain(Temperature main) {
        this.main = main;
    }
}
