package com.bingoflights.service;

import com.bingoflights.model.Airport;
import com.bingoflights.model.Flight;
import com.bingoflights.model.Flights;
import com.bingoflights.model.Location;
import com.bingoflights.model.FlightsDTO;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

public class FlightsJsonSerializerTest {

    @Test
    public void testSerializeFlightsToJson() throws JAXBException {
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

        Flights flights = new Flights();
        flights.add(flight);
        FlightsDTO flightsDTO = new FlightsDTO();
        flightsDTO.setErrors(Boolean.valueOf(false));

        flightsDTO.setFlights(flights.getFlights());

        System.out.println(testObj.serialize(flightsDTO));

    }

}
