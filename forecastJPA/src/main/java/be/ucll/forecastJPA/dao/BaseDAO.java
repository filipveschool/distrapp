package be.ucll.forecastJPA.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by filipve on 24/11/2016.
 */
@Stateless
public abstract class BaseDAO {

    protected static EntityManagerFactory emf;

    public BaseDAO() {
        if ( emf == null ) {
            emf = Persistence.createEntityManagerFactory ( "BeUcll" );
        }
    }

    public void close() {
        if ( emf != null ) {
            emf.close ();
            emf = null;
        }
    }

}
