package be.ucll.forecast.parser.controller;

import be.ucll.forecast.domain.WeatherRasp;
import be.ucll.forecast.parser.datareader.ReaderFactory;
import be.ucll.forecast.parser.filter.BasicAuthenticationFilter;
import be.ucll.forecast.parser.service.RaspberryService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by filip on 18/12/2016.
 */
@BasicAuthenticationFilter
@Path("/data")
public class Endpoint {

    @EJB
    RaspberryService raspberryService;

    @EJB
    private ReaderFactory factory;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public WeatherRasp getWeatherFromRaspberry() {
        return factory.readWeatherData();
    }

    @GET
    @Path("/temperature")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemperature() {
        System.out.println("\n\n\nDO WE GET HERE??????\n\n\n");
        return Response.status(200).entity("Looks like you are using authentication").build();
    }

    @GET
    @Path("/humidity")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHumidity() {
        return Response.status(200).entity(raspberryService.getHumidity()).build();
    }


}