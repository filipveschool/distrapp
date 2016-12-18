package be.ucll.forecast.api.controller;

import be.ucll.forecast.domain.HumidityRasp;
import be.ucll.forecast.domain.Temperature;
import be.ucll.forecast.domain.TemperatureRasp;
import be.ucll.forecastJPA.dao.*;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.server.internal.process.RespondingContext;

import javax.ejb.EJB;
import javax.faces.component.behavior.ClientBehavior;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by filipve on 28/11/2016.
 */
@Path("/weatherjpa")
public class WeatherJPAController {


    // Injected DAO EJB:
    @EJB(mappedName = "TemperatureRaspDB")
    private TemperatureRaspDB temperatureDAO;
    //private TemperatureDAO temperatureDAO;

    @EJB
    private HumidityRaspDB humidityDAO;
    //private HumidityDAO humidityDAO;

    /************************************************************************
     *
     * Hier komen alle functies voor de raspberry pi connectie.
     *
     ************************************************************************
     ************************************************************************/

    @GET
    @Path("/getdatafromraspberrypi")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getData(@QueryParam("data") String data) {
        Client client = ClientBuilder.newClient();
        /**
         * Dit is voor de Basic Authentication opgave.
         */
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("username", "password");
        client.register(feature);

        WebTarget target = client.target("url");
        JsonObject jsonObject = target.request(MediaType.APPLICATION_JSON_TYPE).get(JsonObject.class);

        /**
         * Het JSONObject van de raspberry ziet er als volgt uit:
         *
         * {
         * temp: 18,5
         * humidity: 52,5
         * }
         *
         */
        try {
            if (!"temperature".equals(data)) {
                // Dit haalt de double waarde uit het JSONObject afkomstig van de raspberry pi van de humidity.
                // Zie hierboven.
                humidityDAO.addHumidity(Double.parseDouble(jsonObject.get("humidity").toString()));
            }
            if (!"humidity".equals("data")) {
                // Dit haalt de double waarde uit het JSONObject afkomstig van de raspberry pi van de temperature.
                // Zie hierboven.
                temperatureDAO.addTemperature(Double.parseDouble(jsonObject.get("temperature").toString()));
            }

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Conflict opgetreden met HTTP CODE 409").build();
        }
        return Response.status(Response.Status.CREATED).entity("Created opgetreden met HTTP CODE 201").build();
    }

    /************************************************************************
     *
     * Hier komen alle functies voor objecten op te halen in een collectie.
     *
     ************************************************************************
     ************************************************************************/

    @GET
    @Path("/temperatures")
    public List<TemperatureRasp> getAllTemperatures() {
        return temperatureDAO.getAllTemperatures();
    }

    //    @GET
//    @Path("/temperaturesbeforedate")
//    public List<TemperatureRasp> getTemperaturesBeforeDate() {
//        return temperatureDAO.getTemperaturesBeforeDate();
//    }
//
    @GET
    @Path("/temperaturesafterdates/{month}/{day}/{year}")
    public List<TemperatureRasp> getTemperaturesAfterDayMonthYearDate(@PathParam("day") Integer day,
                                                                      @PathParam("month") Integer month,
                                                                      @PathParam("year") Integer year) {
        return temperatureDAO.getTemperaturesAfterDayMonthYearDate(day, month, year);
    }

    @GET
    @Path("/temperaturesafterdates/{month}/{year}")
    public List<TemperatureRasp> getTemperaturesAfterMonthYearDate(@PathParam("month") Integer month,
                                                                   @PathParam("year") Integer year) {
        return temperatureDAO.getTemperaturesAfterMonthYearDate(month, year);
    }

    @GET
    @Path("/temperaturesafterdates/{month}/{day}")
    public List<TemperatureRasp> getTemperaturesAfterDayMonthDate(@PathParam("day") Integer day,
                                                                  @PathParam("month") Integer month) {
        return temperatureDAO.getTemperaturesAfterDayMonthDate(day, month);
    }

    @GET
    @Path("/temperaturesafterdates/{day}")
    public List<TemperatureRasp> getTemperaturesAfterDayDate(@PathParam("day") Integer day) {
        return temperatureDAO.getTemperaturesAfterDayDate(day);
    }

    @GET
    @Path("/temperatures/{month}/{day}")
    public List<TemperatureRasp> getTemperaturesOfDayAndMonth(@PathParam("month") Integer month,
                                                              @PathParam("day") Integer day) {
        return temperatureDAO.getTemperaturesOfDayAndMonth(month, day);
    }

    @GET
    @Path("/temperatures/{month}")
    public List<TemperatureRasp> getAllTemperaturesOfMonth(@PathParam("month") Integer month) {
        return temperatureDAO.getTemperaturesOfMonth(month);
    }

    //----------------------------------------------------------------------------------------------------------

    @GET
    @Path("/humiditys/{month}")
    public List<HumidityRasp> getAllHumiditysofMonth(@PathParam("month") Integer month) {

        return humidityDAO.getHumiditysOfMonth(month);
    }

    @GET
    @Path("/humiditys/{month}/{day}")
    public List<HumidityRasp> getHumiditysOfDayAndMonth(@PathParam("month") Integer month, @PathParam("day") Integer day) {
        return humidityDAO.getHumiditysOfDayAndMonth(month, day);
    }

    @GET
    @Path("/humidityssafterdates/{month}/{day}/{year}")
    public List<HumidityRasp> getHumiditysAfterDayMonthYearDate(@PathParam("day") Integer day,
                                                                @PathParam("month") Integer month,
                                                                @PathParam("year") Integer year) {
        return humidityDAO.getHumiditysAfterDayMonthYearDate(day, month, year);
    }

    @GET
    @Path("/humidityssafterdates/{month}/{year}")
    public List<HumidityRasp> getHumiditysAfterMonthYearDate(@PathParam("month") Integer month,
                                                             @PathParam("year") Integer year) {
        return humidityDAO.getHumiditysAfterMonthYearDate(month, year);
    }

    @GET
    @Path("/humidityssafterdates/{month}/{day}")
    public List<HumidityRasp> getHumiditysAfterDayMonthDate(@PathParam("day") Integer day,
                                                            @PathParam("month") Integer month) {
        return humidityDAO.getHumiditysAfterDayMonthDate(day, month);
    }

    @GET
    @Path("/humidityssafterdates/{month}/{day}/{year}")
    public List<HumidityRasp> getHumiditysAfterDayDate(@PathParam("day") Integer day) {
        return humidityDAO.getHumiditysAfterDayDate(day);
    }


    /************************************************************************
     *
     * Hier komen alle functies voor 1 specifiek Object op te halen.
     *
     ************************************************************************
     ************************************************************************/

    @GET
    @Path("/temperature/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TemperatureRasp getTemperatureById(@PathParam("id") Integer id) {
        return temperatureDAO.getById(id);
    }

    @GET
    @Path("/humidity/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HumidityRasp getHumidityById(@PathParam("id") Integer id) {
        return humidityDAO.getById(id);
    }

    //----------------------------------------------------------------------------------------------------------


    /************************************************************
     *
     * Hier komen alle functies voor objecten te verwijderen.
     *
     ************************************************************
     ************************************************************/

    @DELETE
    @Path("/temperature/{id}")
    public void removeTemperatureById(@PathParam("id") Integer id) {
        temperatureDAO.removeById(id);
    }

    //----------------------------------------------------------------------------------------------------------

    @DELETE
    @Path("/humidity/{id}")
    public void removeHumidity(@PathParam("id") Integer id) {
        humidityDAO.removeById(id);
    }


}
