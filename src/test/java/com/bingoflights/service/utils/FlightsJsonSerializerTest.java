package com.bingoflights.service.utils;

import com.bingoflights.model.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;

import static com.bingoflights.testing.DataLoader.loadTestData;

public class FlightsJsonSerializerTest {

    private FlightsJsonSerializer testObj;
    private DateTimeZone timeZone = DateTimeZone.forID("Etc/GMT-1");

    @Before
    public void setup(){
        testObj = new FlightsJsonSerializer(timeZone);
    }

    @Test
    public void testSerializeFlightsToJson() throws JAXBException, JSONException, IOException {
        ScheduledFlights scheduledFlights = createScheduledFlights();
        String flightJson = testObj.serialize(scheduledFlights);
        String expectedJson = loadTestData("json/flights.json");
        JSONAssert.assertEquals(expectedJson, flightJson, true);
    }

    @Test
    public void testSerializeNoFlightsToJson() throws IOException, JSONException {
        ScheduledFlights scheduledFlights = createEmptyScheduledFlights(false);
        String noFlightsJson = testObj.serialize(scheduledFlights);
        String expectedJson = loadTestData("json/empty_flights.json");
        JSONAssert.assertEquals(expectedJson, noFlightsJson, true);
    }

    @Test
    public void testSerializeWithErrorsToJson() throws JSONException, IOException {
        ScheduledFlights scheduledFlights = createEmptyScheduledFlights(true);
        String errorJson = testObj.serialize(scheduledFlights);
        String expectedJson = loadTestData("json/errors_flights.json");
        JSONAssert.assertEquals(expectedJson, errorJson, true);
    }

    private ScheduledFlights createEmptyScheduledFlights(boolean error) {
        ScheduledFlights scheduledFlights = new ScheduledFlights();
        scheduledFlights.setErrors(error);
        scheduledFlights.setFlights(new ArrayList<Flight>(0));
        return scheduledFlights;
    }

    private ScheduledFlights createScheduledFlights() {
        Flight flight = new Flight();
        flight.setFlightNumber("AS125666");

        Carrier carrier = new Carrier();
        carrier.setCode("BSG67000");
        carrier.setName("British Airways");
        carrier.setCountry("UK");

        flight.setCarrier(carrier);

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
        ScheduledFlights scheduledFlights = new ScheduledFlights();
        scheduledFlights.setErrors(false);

        scheduledFlights.setFlights(flights.getFlights());
        return scheduledFlights;
    }

}
