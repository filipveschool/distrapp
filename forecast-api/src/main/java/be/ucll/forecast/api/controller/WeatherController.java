package be.ucll.forecast.api.controller;

import be.ucll.forecast.api.service.ApiService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/weather")
public class WeatherController {

	@EJB
	//@Inject
	ApiService apiService;

	/*
	* EntryPoint to get a forecast
	* */

	@GET
	@Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getForecast(@PathParam("param") String location) {
		return Response.status(200).entity( apiService.getForecast(location) ).build();
	}

}