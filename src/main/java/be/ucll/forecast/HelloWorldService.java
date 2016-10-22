package be.ucll.forecast;
 
import be.ucll.forecast.service.ForecastApiService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/weather")
public class HelloWorldService {


	@EJB
	ForecastApiService forecastApiService;

	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String location) {
		return Response.status(200).entity(forecastApiService.getCurrentObservation(location)).build();
	}
 
}