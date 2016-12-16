package be.ucll.forecastJPA.dao;

import be.ucll.forecast.domain.User;
import be.ucll.forecastJPA.exception.DBException;

import javax.ejb.Remote;
import java.util.Collection;

/**
 * Created by filipve on 16/12/2016.
 */
@Remote
public interface UserDB {

    User getById(Integer id);

    User getByUserName(String userName) throws DBException;

    void addUser( String userName, String passwordNotEncrypted );

    void updateUser( User user );

    void removeUser( User user );

    Collection<User> getAllUsers() throws DBException;

}
