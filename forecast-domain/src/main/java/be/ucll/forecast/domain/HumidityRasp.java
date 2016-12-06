package be.ucll.forecast.domain;

import com.fasterxml.jackson.core.SerializableString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by filip on 6/12/2016.
 */

@Entity
@Table(name = "HUMIDITY")
public class HumidityRasp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    public HumidityRasp() {

    }


}
