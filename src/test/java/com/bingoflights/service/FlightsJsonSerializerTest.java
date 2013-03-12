package com.bingoflights.service;

import com.bingoflights.model.*;
import com.bingoflights.model.ScheduledFlights;
import com.bingoflights.service.dto.ScheduledFlightsDTO;
import com.bingoflights.service.utils.FlightsJsonSerializer;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

public class FlightsJsonSerializerTest {

    @Test
    public void testSerializeFlightsToJson() throws JAXBException, JSONException, IOException {
        FlightsJsonSerializer testObj = new FlightsJsonSerializer();

        Flight flight = new Flight();
        flight.setFlightNumber("AS125666");

        flight.setCarrier("British Airways");

        Location departure = new Location();
        departure.setCountry("UK");
        departure.setCity("London");
        departure.setTime(Calendar.getInstance().getTime());

        Airport departureAirport = new Airport();
        departureAirport.setName("London Heathrow");
        departureAirport.setCode("LHW");

        departure.setAirport(departureAirport);

        Location destination = new Location();
        destination.setCountry("Canada");
        destination.setCity("Vancouver");
        destination.setTime(Calendar.getInstance().getTime());

        Airport destinationAirport = new Airport();
        destinationAirport.setCode("YVR");
        destinationAirport.setName("Vancouver International");

        destination.setAirport(destinationAirport);

        flight.setDeparture(departure);
        flight.setDestination(destination);

        ScheduledFlights flights = new ScheduledFlights();
        flights.add(flight);
        ScheduledFlightsDTO scheduledFlightsDTO = new ScheduledFlightsDTO();
        scheduledFlightsDTO.setErrors(Boolean.valueOf(false));

        scheduledFlightsDTO.setFlights(flights.getFlights());


        String flightJson = testObj.serialize(scheduledFlightsDTO);

        URL allFlightsURL = Resources.getResource("json/flights.json");
        String expectedJson = Resources.toString(allFlightsURL, Charsets.UTF_8);

        JSONAssert.assertEquals(expectedJson,flightJson,true);
        System.out.println(flightJson);

    }

}
