package be.ucll.forecastJPA.dao;

import be.ucll.forecast.domain.TemperatureRasp;
import be.ucll.forecastJPA.exception.DBException;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by filipve on 24/11/2016.
 */

//Originele code werkend
//@Stateless
//public class TemperatureDAO {
//@Dependent
@Stateless(mappedName = "TemperatureRaspDB")
public class TemperatureDAO implements TemperatureRaspDB {

    // Injected database connection:
    @PersistenceContext (unitName = "BeUcll")
    private EntityManager em;

    @Override
    public TemperatureRasp getById( Integer id ) {
        return em.find ( TemperatureRasp.class, id );
    }

    @Override
    public TemperatureRasp getByDateTime( LocalDateTime dateTime ) throws DBException {
        return em.createQuery ( " Select temprasp from TemperatureRasp temprasp" +
                        " WHERE temprasp.dateTime = :dateTime",
                TemperatureRasp.class )
                .setParameter ( "dateTime", dateTime )
                .getSingleResult ();
    }

    @Override
    public void addTemperature( double temperatureData ) {
        TemperatureRasp newTemperature = new TemperatureRasp ( temperatureData );
        em.persist ( newTemperature );
        em.flush ();
    }

    @Override
    public void updateTemperature( TemperatureRasp temperatureRasp ) {
        em.merge ( temperatureRasp );
    }

    @Override
    public void removeById( Integer id ) {
        TemperatureRasp temperatureRasp = getById ( id );
        em.remove ( em.contains ( temperatureRasp ) ? temperatureRasp : em.merge ( temperatureRasp ) );

    }

    @Override
    public List<TemperatureRasp> getAllTemperatures() throws DBException {
        //CriteriaBuilder cb = em.getCriteriaBuilder ();
        //CriteriaQuery<Temperature> criteriaQuery = cb.createQuery ( Temperature.class );
        //criteriaQuery.from ( Temperature.class );
        //TypedQuery<Temperature> temperatureTypedQuery = em.createQuery ( criteriaQuery );
        TypedQuery<TemperatureRasp> temperatureTypedQuery = em.createQuery ( "SELECT t FROM TemperatureRasp t", TemperatureRasp.class );
        return temperatureTypedQuery.getResultList ();
    }


    public TemperatureRasp findById( int id ) {
        CriteriaBuilder cb = em.getCriteriaBuilder ();
        CriteriaQuery<TemperatureRasp> criteriaQuery = cb.createQuery ( TemperatureRasp.class );
        Root<TemperatureRasp> root = criteriaQuery.from ( TemperatureRasp.class );
        criteriaQuery.where ( cb.equal ( root.get ( "id" ), id ) );
        TypedQuery<TemperatureRasp> temperatureTypedQuery = em.createQuery ( criteriaQuery );

        return temperatureTypedQuery.getSingleResult ();
    }

}
