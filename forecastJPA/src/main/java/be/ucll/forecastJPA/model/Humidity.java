package be.ucll.forecastJPA.model;

import com.fasterxml.jackson.core.SerializableString;
import org.hibernate.annotations.Tables;
import org.hibernate.annotations.Tables;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by filip on 6/12/2016.
 */

@Entity
@Table(name = "HUMIDITY")
public class Humidity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    public Humidity() {

    }


}
