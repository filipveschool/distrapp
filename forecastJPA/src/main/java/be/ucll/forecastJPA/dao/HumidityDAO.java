package be.ucll.forecastJPA.dao;

import be.ucll.forecast.domain.HumidityRasp;
import be.ucll.forecast.domain.User;
import be.ucll.forecastJPA.exception.DBException;
import org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by filipve on 16/12/2016.
 */
//@Dependent
@Stateless
public class HumidityDAO implements HumidityRaspDB {

    // Injected database connection:
    @PersistenceContext(unitName = "BeUcll")
    private EntityManager em;

    @Override
    public HumidityRasp getById(Integer id) {
        return em.find(HumidityRasp.class, id);
    }

    @Override
    public HumidityRasp getByDateTime(LocalDateTime dateTime) throws DBException {
//        return em.createQuery ( " Select humidityrasp from HumidityRasp humidityrasp" +
//                        " WHERE humidityrasp.dateTime = :dateTime",
//                HumidityRasp.class )
//                .setParameter ( "dateTime", dateTime )
//                .getSingleResult ();

        return em.createNamedQuery("Humidity.getByDateTime", HumidityRasp.class)
                .setParameter("dateTime", dateTime)
                .getSingleResult();
    }


    @Override
    public void addHumidity(double humidityData) {
        HumidityRasp newHumidity = new HumidityRasp(humidityData);
        em.persist(newHumidity);
        em.flush();
    }

    @Override
    public void updateHumidity(HumidityRasp humidityRasp) {
        em.merge(humidityRasp);
    }

    @Override
    public void removeById(Integer id) {
        HumidityRasp humidityRasp = getById(id);
        em.remove(em.contains(humidityRasp) ? humidityRasp : em.merge(humidityRasp));
    }

    /**
     * @return A collection of Humidity Objects
     * @throws DBException if their are no values found
     */
    @Override
    public List<HumidityRasp> getAllHumiditys() throws DBException {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<HumidityRasp> humidityRaspCriteriaQuery = criteriaBuilder.createQuery(HumidityRasp.class);
            humidityRaspCriteriaQuery.from(HumidityRasp.class);
            TypedQuery<HumidityRasp> humidityRaspTypedQuery = em.createQuery(humidityRaspCriteriaQuery);
            return humidityRaspTypedQuery.getResultList();
            //return em.createQuery("select users from User users", User.class).getResultList();
        } catch (DBException exception) {
            throw new DBException("Get all Humidity objects failed : ", exception);
        }
        //return em.createQuery ( "select humiditys from HumidityRasp humiditys", HumidityRasp.class ).getResultList ();
    }

    @Override
    public List<HumidityRasp> getHumiditysBeforeDate(LocalDateTime dateTime) {
        return em.createNamedQuery("Humidity.getHumiditysBeforeDate", HumidityRasp.class)
                .setParameter("dateTime", dateTime)
                .getResultList();
    }

    @Override
    public List<HumidityRasp> getHumiditysAfterDayMonthYearDate(Integer day, Integer month, Integer year) {
        TypedQuery<HumidityRasp> humidityRaspTypedQuery = em.createQuery("select h from HumidityRasp h " +
                "where function('YEAR',h.dateTime) >= :year and function('MONTH',h.dateTime) >= :month  " +
                "and function('DAY',h.dateTime) > :day ", HumidityRasp.class)
                .setParameter("day", day)
                .setParameter("year", year)
                .setParameter("month", month);
        return humidityRaspTypedQuery.getResultList();
    }

    @Override
    public List<HumidityRasp> getHumiditysAfterMonthYearDate(Integer month, Integer year) {
        TypedQuery<HumidityRasp> humidityRaspTypedQuery = em.createQuery("select h from HumidityRasp h " +
                "where function('YEAR',h.dateTime) >= :year and function('MONTH',h.dateTime) > :month  ", HumidityRasp.class)
                .setParameter("year", year)
                .setParameter("month", month);
        return humidityRaspTypedQuery.getResultList();
    }

    @Override
    public List<HumidityRasp> getHumiditysAfterDayMonthDate(Integer day, Integer month) {
        TypedQuery<HumidityRasp> humidityRaspTypedQuery = em.createQuery("select h from HumidityRasp h " +
                "where function('DAY',h.dateTime) > :day and function('MONTH',h.dateTime) >= :month  ", HumidityRasp.class)
                .setParameter("day", day)
                .setParameter("month", month);
        return humidityRaspTypedQuery.getResultList();
    }

    @Override
    public List<HumidityRasp> getHumiditysAfterDayDate(Integer day) {
        TypedQuery<HumidityRasp> humidityRaspTypedQuery = em.createQuery("select h from TemperatureRasp h " +
                "where function('DAY',h.dateTime) > :dayvalue ", HumidityRasp.class)
                .setParameter("dayvalue", day);
        return humidityRaspTypedQuery.getResultList();
    }

    @Override
    public List<HumidityRasp> getHumiditysOfLocalDateTime(LocalDateTime dateTime) {

        int monthvalue = dateTime.getMonthValue();
        int dayvalue = dateTime.getDayOfMonth();
        TypedQuery<HumidityRasp> humidityRaspTypedQuery = em.createQuery("select h from HumidityRasp h " +
                "where function('MONTH',h.dateTime) = :monthvalue and function('DAY',h.dateTime) = :dayvalue ", HumidityRasp.class)
                .setParameter("monthvalue", monthvalue)
                .setParameter("dayvalue", dayvalue);
        return humidityRaspTypedQuery.getResultList();

    }

    @Override
    public List<HumidityRasp> getHumiditysOfDayAndMonth(Integer monthvalue, Integer dayvalue) {
        TypedQuery<HumidityRasp> humidityRaspTypedQuery = em.createQuery("select h from HumidityRasp h " +
                "where function('MONTH',h.dateTime) = :monthvalue and function('DAY',h.dateTime) = :dayvalue ", HumidityRasp.class)
                .setParameter("monthvalue", monthvalue)
                .setParameter("dayvalue", dayvalue);

        return humidityRaspTypedQuery.getResultList();
    }

    @Override
    public List<HumidityRasp> getHumiditysOfMonth(Integer monthvalue) {
        TypedQuery<HumidityRasp> humidityRaspTypedQuery = em.createQuery("select h from HumidityRasp h " +
                "where function('MONTH',h.dateTime) = :monthvalue", HumidityRasp.class)
                .setParameter("monthvalue", monthvalue);
        return humidityRaspTypedQuery.getResultList();
    }
}
