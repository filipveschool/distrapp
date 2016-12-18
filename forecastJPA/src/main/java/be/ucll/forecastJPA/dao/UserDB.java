package be.ucll.forecastJPA.dao;

import be.ucll.forecast.domain.User;
import be.ucll.forecastJPA.exception.DBException;

import javax.ejb.Remote;
import java.util.Collection;
import java.util.List;

/**
 * Created by filipve on 16/12/2016.
 */
@Remote
public interface UserDB {

    User getById(Integer id);

    User getByUserName(String userName) throws DBException;

    void addUserByUsernameAndPassword( String userName, String passwordNotEncrypted );

    void addUser( User user);

    void updateUser( User user );

    void removeUser( User user );

    List<User> getAllUsers() throws DBException;

}
