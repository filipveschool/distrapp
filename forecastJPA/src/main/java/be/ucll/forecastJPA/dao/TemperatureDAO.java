package be.ucll.forecastJPA.dao;

import be.ucll.forecastJPA.model.Temperature;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by filipve on 24/11/2016.
 */

@Stateless
public class TemperatureDAO extends BaseDAO {

    // Injected database connection:
    //@PersistenceContext(unitName = "BeUcll")
    EntityManager em;

    public TemperatureDAO() {
        em = emf.createEntityManager ();
        em.getTransaction ().begin ();
    }


    public List<Temperature> getTemperatures() {
        //CriteriaBuilder cb = em.getCriteriaBuilder ();
        //CriteriaQuery<Temperature> criteriaQuery = cb.createQuery ( Temperature.class );
        //criteriaQuery.from ( Temperature.class );
        //TypedQuery<Temperature> temperatureTypedQuery = em.createQuery ( criteriaQuery );
        TypedQuery<Temperature> temperatureTypedQuery = em.createQuery(
                "SELECT t FROM Temperature t", Temperature.class);
        return temperatureTypedQuery.getResultList ();
    }

    public Temperature findById( int id ) {
        CriteriaBuilder cb = em.getCriteriaBuilder ();
        CriteriaQuery<Temperature> criteriaQuery = cb.createQuery ( Temperature.class );
        Root<Temperature> root = criteriaQuery.from ( Temperature.class );
        criteriaQuery.where ( cb.equal ( root.get ( "id" ), id ) );
        TypedQuery<Temperature> temperatureTypedQuery = em.createQuery ( criteriaQuery );

        return temperatureTypedQuery.getSingleResult ();
    }

    public void delete( Temperature temperature ) {
        em.remove ( temperature );
    }

    public void update( Temperature temperature ) {
        em.merge ( temperature );
    }

    public void save( Temperature temperature ) {
        em.persist ( temperature );
    }

    @Override
    public void close() {
        if ( em != null ) {
            em.getTransaction ().commit ();
            em.close ();
            super.close ();
        }
    }
}
