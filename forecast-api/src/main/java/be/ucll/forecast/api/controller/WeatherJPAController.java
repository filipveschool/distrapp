package be.ucll.forecast.api.controller;

import be.ucll.forecastJPA.dao.TemperatureDAO;
import be.ucll.forecastJPA.model.Temperature;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by filipve on 28/11/2016.
 */
@Path ("/weatherjpa")
public class WeatherJPAController {


    // Injected DAO EJB:
    @EJB
    TemperatureDAO temperatureDAO;

 	/*
    * EntryPoint to get a forecast
	* */

    @GET
    @Path ("/locaties")
    //@Produces (MediaType.APPLICATION_JSON)
//    public Collection<Temperature> getForecast2() {
    public List<Temperature> getForecast() {
//        Collection<Temperature> temperatures = temperatureDAO.getTemperatures ();
        List<Temperature> temperatures = temperatureDAO.getTemperatures ();
        //temperatureDAO.close ();
        return temperatures;
    }

}
