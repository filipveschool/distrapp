package be.ucll.forecast.api.filters;

import be.ucll.forecast.api.generator.KeyGenerator;
import be.ucll.forecast.api.generator.PasswordHashGenerator;
import io.jsonwebtoken.Jwts;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by filip on 18/12/2016.
 */

@Provider
@JWTSecuredFilter
public class JWTSecuredFilterImplementation implements ContainerRequestFilter {

    @EJB
    private PasswordHashGenerator generator;

    @EJB
    private KeyGenerator keyGenerator;

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

        //TODO nog comments verwijderen waar nodig zoals de lijn hieronder
        //        request.setAttribute("hash", generator.generatePBKDF2Hash("veryComplexPassword"));
        try {
            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer".length()).trim();
            Jwts.parser().setSigningKey(keyGenerator.generateJWTSigningKey()).parseClaimsJws(token);
        } catch (Exception ex) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("HTTP 401 Unauthorized")
                    .build());
        }
    }
}
