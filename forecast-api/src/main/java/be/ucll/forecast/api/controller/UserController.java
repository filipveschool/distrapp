package be.ucll.forecast.api.controller;

import be.ucll.forecast.api.filters.JWTSecuredFilter;
import be.ucll.forecast.api.generator.KeyGenerator;
import be.ucll.forecast.api.generator.PasswordHashGenerator;
import be.ucll.forecast.domain.User;
import be.ucll.forecastJPA.dao.UserDB;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URL;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

/**
 * Created by filip on 18/12/2016.
 */
//@JWTSecuredFilter
@Path("/authentication")
public class UserController {

    @EJB
    private UserDB userDB;

    @EJB
    private PasswordHashGenerator generator;

    @EJB
    private KeyGenerator keyGenerator;

    @Context
    private UriInfo uriInfo;

    //@EJB
    private Logger logger;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUser(User userData) {

        //User user = service.getUserByUserName( userData.getUsername() );

        User user = userDB.getByUserName(userData.getUserName());

        if (user == null || !user.passwordEqualsEncryptedPassword(userData.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("You are not authorized").build();
        }

        String jwtToken = issueToken(userData.getUserName());

        return Response.ok().header(AUTHORIZATION, "Bearer " + jwtToken).entity("HTTP 200 OK").build();
    }

    private String issueToken(String login) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        logger.info("#### generating token for a key : " + jwtToken + " - " + key);
        return jwtToken;
    }

    @POST
    @Path("/newuser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser(User user) {
        try {
            //logger.info("username: " + user.getUserName() + " - password: " + user.getPassword());
            userDB.addUserByUsernameAndPassword(user.getUserName(), user.getPassword());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("HTTP 500 Internal Server Error").build();
        }
        return Response.status(Response.Status.CREATED).entity("HTTP 201 Created").build();

        //return Response.created(uriInfo.getAbsolutePathBuilder().path(user.getId().toString()).build()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        User user = userDB.getById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }


    @GET
    @Path(("/allusers"))
    public List<User> getAll() {
        return userDB.getAllUsers();
    }

    // ======================================
    // =          Private methods           =
    // ======================================

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @GET
    @Path("/addusertest")
    public User addUser() {
        User etstUser = new User("ttt","hhh");
        //etstUser.setPassword("ttt");
//        etstUser.encryptPassword("ttt");
        //etstUser.setUserName("hhh");
        logger.info(etstUser.getPassword() + " - " + etstUser.getUserName());
        userDB.addUser(etstUser);
        return etstUser;
        //return etstUser;
    }
}
