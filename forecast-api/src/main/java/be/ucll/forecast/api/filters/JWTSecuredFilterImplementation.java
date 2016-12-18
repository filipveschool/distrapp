package be.ucll.forecast.api.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

/**
 * Created by filip on 18/12/2016.
 */

public class JWTSecuredFilterImplementation implements ContainerRequestFilter {

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

        try {

        } catch (IOException ex) {
            throw new IOException("ex", ex);
        }
    }
