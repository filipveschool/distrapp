package be.ucll.forecast.api.controller;

import be.ucll.forecast.api.filters.JWTSecuredFilter;
import be.ucll.forecast.api.generator.KeyGenerator;
import be.ucll.forecast.api.generator.PasswordHashGenerator;
import be.ucll.forecast.domain.User;
import be.ucll.forecastJPA.dao.UserDB;
import io.jsonwebtoken.Jwts;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.security.Key;
import java.util.Calendar;
import java.util.List;

/**
 * Created by filip on 18/12/2016.
 */
@JWTSecuredFilter
@Path("/authentication")
public class UserController {

    @EJB
    private UserDB userDB;

    @EJB
    private PasswordHashGenerator generator;

    @EJB
    private KeyGenerator keyGenerator;

    //@EJB
    //private

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUser(User userData) {

        //        User user = service.getUserByUserName( userData.getUsername() );

        User user = userDB.getByUserName(userData.getUserName());

        if (user == null || !user.passwordEqualsEncryptedPassword(userData.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("You are not authorized").build();
        }

        String jwtToken = generateJwtTokenWithUsername(userData.getUserName());

    }

    private String generateJwtTokenWithUsername(String userName) {
        Key key = keyGenerator.generateJWTSigningKey();

        Calendar expire = Calendar.getInstance();
        expire.add(Calendar.HOUR, 12);

        String jwtTokenBuilded = Jwts.builder().setSubject(userName).setIssuer(URL.)
    }

    @GET
    @Path(("/allusers"))
    public List<User> getAll() {
        return userDB.getAllUsers();
    }
}
