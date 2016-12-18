package be.ucll.forecast.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by filip on 6/12/2016.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Humidity.getAll",
                query = "select h from HumidityRasp h"),
        @NamedQuery(name = "Humidity.getByDateTime",
                query = "select h from HumidityRasp h where h.dateTime= :dateTime"),
        @NamedQuery(name = "Humidity.findById",
                query = "select h from HumidityRasp h where h.id= :id"),
        @NamedQuery(name = "Humidity.getHumiditysBeforeDate",
                query = "select h from HumidityRasp h where h.dateTime < :dateTime"),
        @NamedQuery(name = "Humidity.getHumiditysAfterDate",
                query = "select h from HumidityRasp h where h.dateTime > :dateTime")
//        ,
//        @NamedQuery(
//                name = "Humidity.getHumiditysOfDate",
//                query = "select h from HumidityRasp h" +
//                        "where FUNCTION('MONTH',h.dateTime) = '12' ")
})
@Table(name = "humiditys")
public class HumidityRasp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "{NotNull.Humidity.datetime}")
    @Column(name = "dateTime", unique = true)
    private LocalDateTime dateTime;


    @Min(value = 0, message = "{Min.Humidity.data}")
    @Max(value = 100, message = "{Max.Humidity.data}")
    @JsonProperty("humidityData")
    @Column(name = "humidity")
    private double humidityData;

    public HumidityRasp() {

    }

    public HumidityRasp(double humidityData) {
        setDateTime(LocalDateTime.now());
        setHumidityData(humidityData);
    }

    public HumidityRasp(double humidityData, LocalDateTime dateTime) {
        setDateTime(dateTime);
        setHumidityData(humidityData);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getHumidityData() {
        return humidityData;
    }

    public void setHumidityData(double humidityData) {
        this.humidityData = humidityData;
    }
}
