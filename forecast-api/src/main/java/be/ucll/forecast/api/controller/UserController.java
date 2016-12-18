package be.ucll.forecast.api.controller;

import be.ucll.forecast.domain.User;
import be.ucll.forecastJPA.dao.UserDB;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by filip on 18/12/2016.
 */
@Path("/authentication")
public class UserController {

    @EJB
    private UserDB userDB;

    //@EJB
    //private

    @GET
    @Path(("/allusers"))
    public List<User> getAll(){
        return userDB.getAllUsers();
    }
}
