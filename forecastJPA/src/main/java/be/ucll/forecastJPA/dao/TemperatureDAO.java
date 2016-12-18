package be.ucll.forecastJPA.dao;

import be.ucll.forecast.domain.Temperature;
import be.ucll.forecast.domain.TemperatureRasp;
import be.ucll.forecastJPA.exception.DBException;
import org.hibernate.query.Query;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Collection;
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
    @PersistenceContext(unitName = "BeUcll")
    private EntityManager em;

    @Override
    public TemperatureRasp getById(Integer id) {
        return em.find(TemperatureRasp.class, id);
    }

    @Override
    public TemperatureRasp getByDateTime(LocalDateTime dateTime) throws DBException {
//        return em.createQuery ( " Select temprasp from TemperatureRasp temprasp" +
//                        " WHERE temprasp.dateTime = :dateTime",
//                TemperatureRasp.class )
//                .setParameter ( "dateTime", dateTime )
//                .getSingleResult ();

        return em.createNamedQuery("Temperature.getByDateTime", TemperatureRasp.class)
                .setParameter("dateTime", dateTime)
                .getSingleResult();
    }

    @Override
    public void addTemperature(double temperatureData) {
        TemperatureRasp newTemperature = new TemperatureRasp(temperatureData);
        em.persist(newTemperature);
        em.flush();
    }

    @Override
    public void updateTemperature(TemperatureRasp temperatureRasp) {
        em.merge(temperatureRasp);
    }

    @Override
    public void removeById(Integer id) {
        TemperatureRasp temperatureRasp = getById(id);
        em.remove(em.contains(temperatureRasp) ? temperatureRasp : em.merge(temperatureRasp));

    }

    @Override
    public List<TemperatureRasp> getAllTemperatures() throws DBException {
        try {
            //CriteriaBuilder cb = em.getCriteriaBuilder();
            //Query<TemperatureRasp> temperatureRaspTypedQuery = em.createNamedQuery("Temperature.getAll");
            return em.createNamedQuery("Temperature.getAll", TemperatureRasp.class).getResultList();
            //return temperatureRaspTypedQuery.getResultList();
        } catch (DBException exception) {
            throw new DBException("Get all Temperature objects failed : ", exception);
        }
    }

    @Override
    public List<TemperatureRasp> getTemperaturesBeforeDate(LocalDateTime dateTime) {
        return em.createNamedQuery("Temperature.getTemperaturesBeforeDate", TemperatureRasp.class)
                .setParameter("dateTime", dateTime)
                .getResultList();
    }

    @Override
    public List<TemperatureRasp> getTemperaturesAfterDayMonthYearDate(Integer day, Integer month, Integer year) {
        TypedQuery<TemperatureRasp> temperatureRaspTypedQuery = em.createQuery("select h from TemperatureRasp h " +
                "where function('YEAR',h.dateTime) >= :year and function('MONTH',h.dateTime) >= :month  and function('DAY',h.dateTime) > :day ", TemperatureRasp.class)
                .setParameter("day", day)
                .setParameter("year", year)
                .setParameter("month", month);
        return temperatureRaspTypedQuery.getResultList();
    }

    @Override
    public List<TemperatureRasp> getTemperaturesAfterMonthYearDate(Integer month, Integer year) {
        TypedQuery<TemperatureRasp> temperatureRaspTypedQuery = em.createQuery("select h from TemperatureRasp h " +
                "where function('YEAR',h.dateTime) >= :year and function('MONTH',h.dateTime) > :month  ", TemperatureRasp.class)
                .setParameter("year", year)
                .setParameter("month", month);
        return temperatureRaspTypedQuery.getResultList();
    }

    @Override
    public List<TemperatureRasp> getTemperaturesAfterDayMonthDate(Integer day, Integer month) {
        TypedQuery<TemperatureRasp> temperatureRaspTypedQuery = em.createQuery("select h from TemperatureRasp h " +
                "where function('DAY',h.dateTime) > :day and function('MONTH',h.dateTime) >= :month  ", TemperatureRasp.class)
                .setParameter("day", day)
                .setParameter("month", month);
        return temperatureRaspTypedQuery.getResultList();
    }

    @Override
    public List<TemperatureRasp> getTemperaturesAfterDayDate(Integer dayvalue) {

        TypedQuery<TemperatureRasp> temperatureRaspTypedQuery = em.createQuery("select h from TemperatureRasp h " +
                "where function('DAY',h.dateTime) > :dayvalue ", TemperatureRasp.class)
                .setParameter("dayvalue", dayvalue);
        return temperatureRaspTypedQuery.getResultList();
    }


    @Override
    public List<TemperatureRasp> getTemperaturesOfLocalDateTime(LocalDateTime dateTime) {

        int monthvalue = dateTime.getMonthValue();
        int dayvalue = dateTime.getDayOfMonth();
        TypedQuery<TemperatureRasp> temperatureRaspTypedQuery = em.createQuery("select h from TemperatureRasp h " +
                "where function('MONTH',h.dateTime) = :monthvalue and function('DAY',h.dateTime) = :dayvalue ", TemperatureRasp.class)
                .setParameter("monthvalue", monthvalue)
                .setParameter("dayvalue", dayvalue);
        return temperatureRaspTypedQuery.getResultList();

    }


    @Override
    public List<TemperatureRasp> getTemperaturesOfDayAndMonth(Integer monthvalue, Integer dayvalue) {

        TypedQuery<TemperatureRasp> temperatureRaspTypedQuery = em.createQuery("select h from TemperatureRasp h " +
                "where function('MONTH',h.dateTime) = :monthvalue and function('DAY',h.dateTime) = :dayvalue ", TemperatureRasp.class)
                .setParameter("monthvalue", monthvalue)
                .setParameter("dayvalue", dayvalue);

        return temperatureRaspTypedQuery.getResultList();

    }

    @Override
    public List<TemperatureRasp> getTemperaturesOfMonth(Integer monthvalue) {

        TypedQuery<TemperatureRasp> temperatureRaspTypedQuery = em.createQuery("select h from TemperatureRasp h " +
                "where function('MONTH',h.dateTime) = :monthvalue", TemperatureRasp.class)
                .setParameter("monthvalue", monthvalue);
        return temperatureRaspTypedQuery.getResultList();

    }


}
