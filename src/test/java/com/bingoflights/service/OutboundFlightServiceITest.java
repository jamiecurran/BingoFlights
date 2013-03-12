package com.bingoflights.service;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class OutboundFlightServiceITest {

    @Test
    public void testGetFlightDepartingFromCityJSONStructure() throws IOException, JSONException {
        OutboundFlightService testObj = new OutboundFlightServiceImpl();
        Calendar flightDate = new GregorianCalendar(2013,3,17);
        String city = "New York";
        String flightsJSON = testObj.getFlightsDepartingFromCity(city, flightDate);

        URL allFlightsURL = Resources.getResource("json/london_flights.json");
        String londonFlights = Resources.toString(allFlightsURL, Charsets.UTF_8);

        JSONAssert.assertEquals(londonFlights, flightsJSON, true);


    }

    @Test
    public void testReturnsNewYorkFlightOnSpecificDate(){

    }

    @Test
    public void testReturnsNoFlightsOnOtherDate(){

    }

    @Test
    public void testReturnsNoFlightsGivenOtherCity(){

    }

    @Test
    public void testJSONErrorVariableIsTrueIfSchedulerIsDown(){

    }




}
