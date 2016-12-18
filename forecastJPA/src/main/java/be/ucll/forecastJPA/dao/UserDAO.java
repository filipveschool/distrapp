package be.ucll.forecastJPA.dao;

import be.ucll.forecast.domain.User;
import be.ucll.forecastJPA.exception.DBException;
import org.hibernate.Criteria;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

/**
 * Created by filipve on 16/12/2016.
 */
@Stateless
public class UserDAO implements UserDB {

    @PersistenceContext(unitName = "BeUcll")
    private EntityManager em;

    /**
     * This gets an object by using its id out of the database?
     * This is the R or READ from the CRUD.
     *
     * @param id
     * @return
     * @throws DBException Throws an Exception if Object is not found or key is invalid
     */
    @Override
    public User getById(Integer id) throws DBException {
        try {
            return em.find(User.class, id);
        } catch (DBException exception) {
            throw new DBException("Get object from database is failed: " + id, exception);
        }
    }

    /**
     * Get a user by choosing his usernames as variable
     *
     * @param userName
     * @return User object
     * @throws DBException
     */
    @Override
    public User getByUserName(String userName) throws DBException {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userCriteriaQuery.from(User.class);
        userCriteriaQuery.where(criteriaBuilder.equal(userRoot.get("userName"), userName));
        TypedQuery<User> userTypedQuery = em.createQuery(userCriteriaQuery);
        return userTypedQuery.getSingleResult();
//        return em.createQuery(" Select user from User user WHERE user.userName = :userName",
//                User.class)
//                .setParameter("userName", userName)
//                .getSingleResult();
    }

    /**
     * This saves a new User Object to the database
     * This the C or Create from th CRUD. Persist is just the terms that JPA uses.
     *
     * @param userName
     * @param passwordNotEncrypted
     * @throws DBException Throws an Exception if transaction failed
     */
    @Override
    public void addUser(String userName, String passwordNotEncrypted) throws DBException {
        User newUser = new User(userName, passwordNotEncrypted);
        try {
            em.persist(newUser);
            em.flush();
        } catch (DBException exception) {
            throw new DBException("User object couldn't be added to the database:" + newUser, exception);
        }
    }

    /**
     * This updates an Entity object in the database
     * This is the U or UPDATE from the CRUD.
     *
     * @param user
     * @throws DBException Throws an Exception if object that should be updated doesn't exist
     */
    @Override
    public void updateUser(User user) throws DBException {
        try {
            em.merge(user);
        } catch (DBException exception) {
            throw new DBException("Updating User object in database failed : " + user, exception);
        }
    }

    /**
     * This deletes an Entity object from the database
     * This is the D or Delete from the CRUD. remove is just the terms that JPA uses.
     *
     * @param user
     * @throws DBException hrows an Exception if object that should be removed doesn't exist
     */
    @Override
    public void removeUser(User user) throws DBException {
        try {
            em.remove(em.contains(user) ? user : em.merge(user));
        } catch (DBException exception) {
            throw new DBException("User Object couldn't be removed from database: " + user, exception);
        }

    }

    /**
     * @return A collection of Entity Objects
     * @throws DBException if their are no values found
     */
    @Override
    public List<User> getAllUsers() throws DBException {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);
            userCriteriaQuery.from(User.class);
            TypedQuery<User> userTypedQuery = em.createQuery(userCriteriaQuery);
            return userTypedQuery.getResultList();
            //return em.createQuery("select users from User users", User.class).getResultList();
        } catch (DBException exception) {
            throw new DBException("Get all User objects failed : ", exception);
        }
    }
}
