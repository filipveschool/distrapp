package be.ucll.forecast.api.controller;

import be.ucll.forecast.api.service.ApiService;
import be.ucll.forecastJPA.dao.TemperatureDAO;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/weather")
public class WeatherController {


    //@Inject
    @EJB
    ApiService apiService;


    public WeatherController() {
    }

    /**
     * EntryPoint to get a forecast
     */

    /**
     * @param location
     * @return forecast van de stad die je via {param} meegeeft in de url
     */
    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON) //Hiermee geef je aan dat je JSON uitvoer wil
    public Response getForecast(@PathParam("param") String location) {
        return Response.status(200).entity(apiService.getForecast(location)).build();
    }






}