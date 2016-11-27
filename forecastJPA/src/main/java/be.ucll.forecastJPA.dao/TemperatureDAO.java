package be.ucll.forecastJPA.dao;

import javax.persistence.EntityManager;

/**
 * Created by filipve on 24/11/2016.
 */
public class TemperatureDAO extends BaseDAO {

    EntityManager em;

    public TemperatureDAO() {
        em = emf.createEntityManager ();
        em.getTransaction ().begin ();
    }

}
