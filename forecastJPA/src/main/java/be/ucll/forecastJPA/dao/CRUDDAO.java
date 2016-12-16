package be.ucll.forecastJPA.dao;

import javax.ejb.Remote;
import javax.persistence.Entity;

/**
 * Created by filip on 6/12/2016.
 */

@Remote
public interface CRUDDAO {

    void save(Entity entity);

}
