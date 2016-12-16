package be.ucll.forecast.domain;


import javax.persistence.*;
import java.io.Serializable;

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
@Table (name = "TEMPERATURES", schema = "APP")
public class TemperatureRasp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column (name = "DAY")
    private double day;

    //private double

    @Column (name = "MINIMUM")
    private double min;

    @Column (name = "MAXIMUM")
    private double max;

    public TemperatureRasp() {

    }

    public TemperatureRasp( int id ) {
        setId ( id );
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public double getDay() {
        return day;
    }

    public void setDay( double day ) {
        this.day = day;
    }

    public double getMin() {
        return min;
    }

    public void setMin( double min ) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax( double max ) {
        this.max = max;
    }


}
