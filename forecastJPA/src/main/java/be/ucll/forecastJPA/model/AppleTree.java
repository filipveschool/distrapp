package be.ucll.forecastJPA.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Created by filipve on 28/11/2016.
 */
@Entity
public class AppleTree {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    @Temporal (TemporalType.DATE)
    private Date dob;

    @Transient
    private int age;

    @OneToMany(mappedBy = "appleTree")
    private List<Apple> apples;

    public AppleTree() {

    }

    @PostLoad
    public void postLoad() {
        Calendar dobCal = Calendar.getInstance();
        dobCal.setTime(dob);
        Calendar today = Calendar.getInstance();

        age = today.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public List<Apple> getApples() {
        return apples;
    }

    public void setApples(List<Apple> apples) {
        this.apples = apples;
    }
}
