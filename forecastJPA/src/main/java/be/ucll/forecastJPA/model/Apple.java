package be.ucll.forecastJPA.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by filipve on 28/11/2016.
 */
@Entity
public class Apple {
    @Id
    @GeneratedValue
    private int id;

    @Column(length = 64)
    private String colour;

    @ManyToOne
    @JoinColumn(name = "appletree_id")
    private AppleTree appleTree;

    public Apple() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String name) {
        this.colour = name;
    }

    public AppleTree getAppleTree() {
        return appleTree;
    }

    public void setAppleTree(AppleTree appleTree) {
        this.appleTree = appleTree;
    }

}
