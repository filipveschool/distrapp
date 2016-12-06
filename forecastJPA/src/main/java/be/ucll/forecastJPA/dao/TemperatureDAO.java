package be.ucll.forecastJPA.dao;

import be.ucll.forecast.domain.TemperatureRasp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by filipve on 24/11/2016.
 */

@Stateless
public class TemperatureDAO {

    // Injected database connection:
    @PersistenceContext(unitName = "BeUcll")
    private EntityManager em;

//    public TemperatureDAO() {
//        em = emf.createEntityManager ();
//        em.getTransaction ().begin ();
//    }


    public List<TemperatureRasp> getTemperatures() {
        //CriteriaBuilder cb = em.getCriteriaBuilder ();
        //CriteriaQuery<Temperature> criteriaQuery = cb.createQuery ( Temperature.class );
        //criteriaQuery.from ( Temperature.class );
        //TypedQuery<Temperature> temperatureTypedQuery = em.createQuery ( criteriaQuery );
        TypedQuery<TemperatureRasp> temperatureTypedQuery = em.createQuery("SELECT t FROM TemperatureRasp t", TemperatureRasp.class);
        return temperatureTypedQuery.getResultList();
    }

    public TemperatureRasp findById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TemperatureRasp> criteriaQuery = cb.createQuery(TemperatureRasp.class);
        Root<TemperatureRasp> root = criteriaQuery.from(TemperatureRasp.class);
        criteriaQuery.where(cb.equal(root.get("id"), id));
        TypedQuery<TemperatureRasp> temperatureTypedQuery = em.createQuery(criteriaQuery);

        return temperatureTypedQuery.getSingleResult();
    }

    public void delete(TemperatureRasp temperature) {
        em.remove(temperature);
    }

    public void update(TemperatureRasp temperature) {
        em.merge(temperature);
    }

    public void save(TemperatureRasp temperature) {
        em.persist(temperature);
    }

    /**
     * example
     */
    public void createTemperature() {
        final TemperatureRasp temperature = new TemperatureRasp();
        temperature.setDay(1);
        temperature.setMin(2);
        temperature.setMax(4);
        em.persist(temperature);
    }

//    @Override
//    public void close() {
//        if ( em != null ) {
//            em.getTransaction ().commit ();
//            em.close ();
//            super.close ();
//        }
//    }
}
