package be.ucll.forecast.api.controller;

import be.ucll.forecast.domain.Temperature;
import be.ucll.forecast.domain.TemperatureRasp;
import be.ucll.forecastJPA.dao.TemperatureDAO;

import javax.ejb.EJB;
import javax.persistence.Entity;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by filipve on 28/11/2016.
 */
@Path("/weatherjpa")
public class WeatherJPAController {


    // Injected DAO EJB:
    @EJB
    TemperatureDAO temperatureDAO;

 	/*
    * EntryPoint to get a forecast
	* */

    @GET
    @Path("/locaties")
    //@Produces (MediaType.APPLICATION_JSON)
//    public Collection<Temperature> getForecast2() {
    public List<TemperatureRasp> getForecast() {
//        Collection<Temperature> temperatures = temperatureDAO.getTemperatures ();
        List<TemperatureRasp> temperatures = temperatureDAO.getTemperatures();
        //temperatureDAO.close ();
        return temperatures;
    }

    @GET
    @Path("/savetemp")
    public void saveTemperature(){
        TemperatureRasp tr = new TemperatureRasp();
        tr.setMin(1);
        tr.setMax(2);
        tr.setDay(1);
        //temperatureDAO.save( tr);
        temperatureDAO.persist( tr);
        //temperatureDAO.addTemp(tr);
    }



}
