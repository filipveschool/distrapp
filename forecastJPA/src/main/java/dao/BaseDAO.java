package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by filipve on 24/11/2016.
 */
public abstract class BaseDAO {

    protected static EntityManagerFactory emf;

    public BaseDAO() {
        if ( emf == null ) {
            emf = Persistence.createEntityManagerFactory ( "be.ucll" );
        }
    }

    public void close() {
        if ( emf != null ) {
            emf.close ();
            emf = null;
        }
    }

}
