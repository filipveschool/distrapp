package be.ucll.forecast.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by filipve on 28/11/2016.
 */

/**
 * Met @Entity zeggen we dat deze klasse een POJO (Plain Old Java Object) is
 * Zodat we hierop ORM(=Object Relational Mapping) kunnen toepassen.
 */
@Entity
//@NamedQueries ({
//        @NamedQuery (name = "Temperature.getAllTemperatures",
//                query = "select t from Temperature t")
//})
@Table (name = "TEMPERATURES")
public class TemperatureRasp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @NotNull (message = "{NotNull.Temperature.datetime}")
    @Column (name = "dateTime", unique = true)
    private LocalDateTime dateTime;

    //@Column (name = "dayofweek")
    //private double day;

    //private double

    @Column (name = "minimumtemperature")
    private double minTemp;

    @Column (name = "maximumtemperature")
    private double maxTemp;

    @Column (name = "tempdata")
    private double tempData;

    public TemperatureRasp() {

    }

    public TemperatureRasp( double tempData ) {
        setDateTime ( LocalDateTime.now () );
        setTempData ( tempData );
    }

    public TemperatureRasp( double tempData, LocalDateTime dateTime ) {
        setDateTime ( dateTime );
        setTempData ( tempData );
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime( LocalDateTime dateTime ) {
        this.dateTime = dateTime;
    }

//    public double getDay() {
//        return day;
//    }
//
//    public void setDay( double day ) {
//        this.day = day;
//    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp( double minTemp ) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp( double maxTemp ) {
        this.maxTemp = maxTemp;
    }

    public double getTempData() {
        return tempData;
    }

    public void setTempData( double tempData ) {
        this.tempData = tempData;
    }

}
