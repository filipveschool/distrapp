package be.ucll.forecastJPA.dao;

import be.ucll.forecast.domain.HumidityRasp;
import be.ucll.forecast.domain.TemperatureRasp;
import be.ucll.forecastJPA.exception.DBException;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by filipve on 16/12/2016.
 */
//@Dependent
@Stateless
public class HumidityDAO implements HumidityRaspDB{

    // Injected database connection:
    @PersistenceContext (unitName = "BeUcll")
    private EntityManager em;

    @Override
    public HumidityRasp getById( Integer id ) {
        return em.find(HumidityRasp.class,id);
    }

    @Override
    public HumidityRasp getByDateTime( LocalDateTime dateTime ) throws DBException {
        return em.createQuery ( " Select humidityrasp from HumidityRasp humidityrasp" +
                        " WHERE humidityrasp.dateTime = :dateTime",
                HumidityRasp.class )
                .setParameter ( "dateTime", dateTime )
                .getSingleResult ();
    }

    @Override
    public void addHumidity( double humidityData ) {
        HumidityRasp newHumidity = new HumidityRasp (humidityData );
        em.persist ( newHumidity );
        em.flush ();
    }

    @Override
    public void updateHumidity( HumidityRasp humidityRasp ) {
    em.merge ( humidityRasp );
    }

    @Override
    public void removeById( Integer id ) {
        HumidityRasp humidityRasp = getById ( id );
        em.remove ( em.contains(humidityRasp) ? humidityRasp : em.merge ( humidityRasp ) );
    }

    @Override
    public List<HumidityRasp> getAllHumiditys() throws DBException {
        return em.createQuery ( "select humiditys from HumidityRasp humiditys", HumidityRasp.class ).getResultList ();
    }


}
