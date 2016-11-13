package be.ucll.forecast.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by tompl on 9/27/2016.
 */

public class Observation {

    private String dt;

    private Temperature temp;

    public Observation() {
    }

    public void setDt(String dt){
        int milli = Integer.parseInt(dt);
        System.out.println("HELLOOOOOOOOOOOOO");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dat = new Date((long)milli*1000);
        this.dt = format.format(dat);
    }

    @JsonProperty(value = "date", access = JsonProperty.Access.READ_ONLY)
    public String getDt() {
        return dt;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }
}
