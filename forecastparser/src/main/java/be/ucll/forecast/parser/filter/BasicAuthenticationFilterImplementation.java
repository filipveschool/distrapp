package be.ucll.forecast.parser.filter;

import org.glassfish.jersey.internal.util.Base64;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

//import com.sun.xml.internal.messaging.saaj.util.Base64;

/**
 * Created by filip on 18/12/2016.
 */
@Provider
@BasicAuthenticationFilter
@Priority(Priorities.AUTHENTICATION)
public class BasicAuthenticationFilterImplementation implements ContainerRequestFilter, ContainerResponseFilter {

    //Dit is het path zoals in de opgave staat voor de weerdata op te halen op de raspberry PI.
    private static final String RASPBERRY_PYTHON_PATH = "/projects/readDHT22.py";

    //Dit is de username voor de opgave van Basic Authentication
    private static final String username_authentication = "FilipTomRaspberryPi";

    //Dit is het paswoord voor de opgave van Basic Authentication
    private static final String password_authentication = "PassWordTomFilipRaspberryPi";

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
            .entity("You cannot access this resource").build();

    private static final Response ACCESS_APPROVED = Response.status(Response.Status.ACCEPTED)
            .entity("You cannot access this resource").build();


    /**
     * Filter method called before a request has been dispatched to a resource.
     * <p>
     * <p>
     * Filters in the filter chain are ordered according to their {@code javax.annotation.Priority}
     * class-level annotation value.
     * If a request filter produces a response by calling {@link ContainerRequestContext#abortWith}
     * method, the execution of the (either pre-match or post-match) request filter
     * chain is stopped and the response is passed to the corresponding response
     * filter chain (either pre-match or post-match). For example, a pre-match
     * caching filter may produce a response in this way, which would effectively
     * skip any post-match request filters as well as post-match response filters.
     * Note however that a responses produced in this manner would still be processed
     * by the pre-match response filter chain.
     * </p>
     *
     * @param requestContext request context.
     * @throws IOException if an I/O exception occurs.
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        requestContext.getHeaders().add(HttpHeaders.WWW_AUTHENTICATE, "Basic message='Basic API private parser as request Tom and Filip'");

        try {
            //Fetch authorization header
            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            //Dubbele punt gebruiken omdat beide waarden in 1 veld opgeslagen worden.
            // De combinate ucll/student wordt dan herwerkt naar ucll:student
            String userPassword = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

            String newBase64String = new String(Base64.encode((username_authentication + ":" + password_authentication).getBytes()));
            if (!userPassword.equals(newBase64String)) {
                throw new WebApplicationException();
            }
        } catch (Exception ex) {
            //If no authorization information present; block access
            requestContext.abortWith(ACCESS_DENIED);

        }
    }

    /**
     * Filter method called after a response has been provided for a request
     * (either by a {@link ContainerRequestFilter request filter} or by a
     * matched resource method.
     * <p>
     * Filters in the filter chain are ordered according to their {@code javax.annotation.Priority}
     * class-level annotation value.
     * </p>
     *
     * @param requestContext  request context.
     * @param responseContext response context.
     * @throws IOException if an I/O exception occurs.
     */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add(HttpHeaders.WWW_AUTHENTICATE, "Basic message='Basic API private parser as response Tom and Filip'");
    }
}
