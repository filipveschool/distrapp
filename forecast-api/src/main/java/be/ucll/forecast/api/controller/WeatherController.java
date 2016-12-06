package be.ucll.forecast.api.controller;

import be.ucll.forecast.api.service.ApiService;
import be.ucll.forecastJPA.dao.TemperatureDAO;
import be.ucll.forecastJPA.model.Temperature;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path ("/weather")
public class WeatherController {


    //@Inject
    @EJB
    ApiService apiService;


    public WeatherController() {
    }

    @GET
    @Path ("/{param}")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getForecast( @PathParam ("param") String location ) {
        return Response.status ( 200 ).entity ( apiService.getForecast ( location ) ).build ();
    }

	/*
    * EntryPoint to get a forecast
	* */

}