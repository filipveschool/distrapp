package be.ucll.forecast.api.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
 * Created by tompl on 10/18/2016.
 */

@Path("/weather2")
public class MainController {

   // @EJB
   // ForecastApiService forecastService;

    @GET
    @Path("x")
    @Produces("application/json")
    public String getObservation() {
        return "x";
        //return forecastService.getCurrentObservation(location);
    }

    @GET
    @Produces("text/html")
    @Path("page")
    public String getHtml() {
        return "<html lang=\"en\"><body><h1>Hello, World!!</body></h1></html>";
    }

}

