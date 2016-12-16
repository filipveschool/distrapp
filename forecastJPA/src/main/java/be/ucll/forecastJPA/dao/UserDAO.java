package be.ucll.forecastJPA.dao;

import be.ucll.forecast.domain.User;
import be.ucll.forecastJPA.exception.DBException;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Created by filipve on 16/12/2016.
 */
//@Dependent
@Stateless
public class UserDAO implements UserDB {

    @PersistenceContext (unitName = "BeUcll")
    private EntityManager em;

    @Override
    public User getById(Integer id){
        return em.find(User.class, id);
    }

    @Override
    public User getByUserName( String userName ) throws DBException {
        return em.createQuery ( " Select user from User user WHERE user.userName = :userName",
                User.class )
                .setParameter ( "userName", userName )
                .getSingleResult ();
    }

    @Override
    public void addUser( String userName, String passwordNotEncrypted ) {
        User newUser = new User(userName, passwordNotEncrypted);
        em.persist ( newUser );
        em.flush ();
    }

    @Override
    public void updateUser( User user ) {
        em.merge ( user );
    }

    //@Override
    public void removeUser(User user){
        em.remove ( em.contains(user) ? user : em.merge ( user ) );
    }

    @Override
    public Collection<User> getAllUsers() throws DBException {
        return em.createQuery ( "select users from User users", User.class ).getResultList ();
    }
}
