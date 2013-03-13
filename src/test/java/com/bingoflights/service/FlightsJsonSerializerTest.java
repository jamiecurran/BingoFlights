package com.bingoflights.service;

import com.bingoflights.model.*;
import com.bingoflights.model.ScheduledFlights;
import com.bingoflights.service.dto.ScheduledFlightsDTO;
import com.bingoflights.service.utils.FlightsJsonSerializer;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FlightsJsonSerializerTest {

    private FlightsJsonSerializer testObj;

    @Before
    public void setup(){
        testObj = new FlightsJsonSerializer();
    }

    @Test
    public void testSerializeFlightsToJson() throws JAXBException, JSONException, IOException {
        ScheduledFlightsDTO scheduledFlightsDTO = createScheduledFlights();
        String flightJson = testObj.serialize(scheduledFlightsDTO);
        String expectedJson = getTestData("json/flights.json");
        JSONAssert.assertEquals(expectedJson, flightJson, true);
    }

    @Test
    public void testSerializeNoFlightsToJson() throws IOException, JSONException {
        ScheduledFlightsDTO scheduledFlightsDTO = createEmptyScheduledFlightsDTO(false);
        String noFlightsJson = testObj.serialize(scheduledFlightsDTO);
        String expectedJson = getTestData("json/empty_flights.json");
        JSONAssert.assertEquals(expectedJson, noFlightsJson, true);
    }

    @Test
    public void testSerializeWithErrorsToJson() throws JSONException, IOException {
        ScheduledFlightsDTO scheduledFlightsDTO = createEmptyScheduledFlightsDTO(true);
        String errorJson = testObj.serialize(scheduledFlightsDTO);
        String expectedJson = getTestData("json/errors_flights.json");
        JSONAssert.assertEquals(expectedJson, errorJson, true);
    }

    private ScheduledFlightsDTO createEmptyScheduledFlightsDTO(boolean error) {
        ScheduledFlightsDTO scheduledFlightsDTO = new ScheduledFlightsDTO();
        scheduledFlightsDTO.setErrors(error);
        scheduledFlightsDTO.setFlights(new ArrayList<Flight>(0));
        return scheduledFlightsDTO;
    }

    private String getTestData(String file) throws IOException {
        URL allFlightsURL = Resources.getResource(file);
        return Resources.toString(allFlightsURL, Charsets.UTF_8);
    }

    private ScheduledFlightsDTO createScheduledFlights() {
        Flight flight = new Flight();
        flight.setFlightNumber("AS125666");

        flight.setCarrier("British Airways");

        Location departure = new Location();
        departure.setCountry("UK");
        departure.setCity("London");
        DateTime departureTime = new DateTime(2013,3,23,9,25).withZone(DateTimeZone.forID("Etc/GMT-1"));
        departure.setTime(departureTime);

        Airport departureAirport = new Airport();
        departureAirport.setName("London Heathrow");
        departureAirport.setCode("LHW");

        departure.setAirport(departureAirport);

        Location destination = new Location();
        destination.setCountry("Canada");
        destination.setCity("Vancouver");

        DateTime arrivalTime = new DateTime(2013,3,23,21,33).withZone(DateTimeZone.forID("Etc/GMT-1"));
        destination.setTime(arrivalTime);

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
        return scheduledFlightsDTO;
    }

}
