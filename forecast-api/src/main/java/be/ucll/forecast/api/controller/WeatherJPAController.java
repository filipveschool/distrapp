package be.ucll.forecast.api.controller;

import be.ucll.forecast.domain.HumidityRasp;
import be.ucll.forecast.domain.Temperature;
import be.ucll.forecast.domain.TemperatureRasp;
import be.ucll.forecastJPA.dao.*;

import javax.ejb.EJB;
import javax.persistence.Entity;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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


 	/*
    * EntryPoint to get a forecast
	* */

    @GET
    @Path("/locaties")
    //@Produces (MediaType.APPLICATION_JSON)
//    public Collection<Temperature> getForecast2() {
    public Collection<TemperatureRasp> getForecast() {
//        Collection<Temperature> temperatures = temperatureDAO.getTemperatures ();
        Collection<TemperatureRasp> temperatures = temperatureDAO.getAllTemperatures();
        //temperatureDAO.close ();
        return temperatures;
    }


    //public Collection<HumidityRasp> getAllHumidityData(){    }

    @GET
    @Path("/alltemperatures")
    public List<TemperatureRasp> getAllTemperatures() {
        return temperatureDAO.getAllTemperatures();
    }

    @GET
    @Path("/alltemperaturesofmonth")
    public List<TemperatureRasp> getAllTemperaturesOfMonth() {

        return temperatureDAO.getTemperaturesOfDate(LocalDateTime.now());
    }

    //public Collection

//    @GET
//    @Path("/savetemp")
//    public void saveTemperature() {
//        TemperatureRasp tr = new TemperatureRasp();
//        tr.setMinTemp(1);
//        tr.setMaxTemp(2);
//        tr.setId(2);
//        //tr.setDay(1);
//        //temperatureDAO.save( tr);
//        temperatureDAO.addTemperature(2);
//        //temperatureDAO.addTemp(tr);
//    }
//
//    @GET
//    @Path("/deletetemp")
//    public void deleteTemp() {
//        temperatureDAO.removeById(1);
//    }
//
//    @GET
//    @Path("/updatetemp")
//    public void updateTemp() {
//        TemperatureRasp tm = temperatureDAO.getById(1);
//        tm.setMaxTemp(10);
//        temperatureDAO.updateTemperature(tm);
//    }

    @GET
    @Path("/allhumiditysofmonth")
    public List<HumidityRasp> getAllHumiditysofMonth() {

        return humidityDAO.getHumiditysOfDate(LocalDateTime.now());
    }

}
