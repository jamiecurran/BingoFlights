package com.bingoflights.dao.utils;

import com.bingoflights.model.Airport;
import com.bingoflights.model.Flight;
import com.bingoflights.model.Location;
import com.bingoflights.model.ScheduledFlights;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static com.bingoflights.testing.DataLoader.loadTestData;

public class FlightsDeserializerTest {

    private String testData;
    private FlightsXMLDeserializer testObj;

    @Before
    public void setup(){
        testObj = new FlightsXMLDeserializer();
    }

    @Test
    public void testDeserializationMappingForFlightNumber() throws IOException {
        testData = loadTestData("xml/international_flights_new_york.xml");
        ScheduledFlights mappedFlight = testObj.deserialize(testData);
        Flight newYorkFlight = mappedFlight.getFlight(0);
        assertThat(newYorkFlight.getFlightNumber(), is("KS125666"));
    }

    @Test
    public void testDeserializationMappingForCarrier() throws IOException {
        testData = loadTestData("xml/international_flights_new_york.xml");
        ScheduledFlights mappedFlight = testObj.deserialize(testData);
        Flight newYorkFlight = mappedFlight.getFlight(0);
        assertThat(newYorkFlight.getCarrier().getName(), is("United Airlines"));
    }

    @Test
    public void testDeserializationMappingForLocation() throws IOException {
        testData = loadTestData("xml/international_flights_new_york.xml");
        ScheduledFlights mappedFlight = testObj.deserialize(testData);
        Flight newYorkFlight = mappedFlight.getFlight(0);
        Location departure = newYorkFlight.getDeparture();
        assertThat(departure.getCity(), is("New York") );
        assertThat(departure.getTime().getDayOfMonth(), is(17));
        assertThat(departure.getTime().getMonthOfYear(), is(3));
        assertThat(departure.getTime().getYear(), is(2013));
    }

    @Test
    public void testDeserializationMappingForAirport() throws IOException {
        testData = loadTestData("xml/international_flights_new_york.xml");
        ScheduledFlights mappedFlight = testObj.deserialize(testData);
        Flight newYorkFlight = mappedFlight.getFlight(0);
        Airport departureAirport = newYorkFlight.getDeparture().getAirport();
        assertThat(departureAirport.getCode(), is("JFK"));
        assertThat(departureAirport.getName(), is("John F. Kennedy International Airport"));
    }

    @Test
    public void testDeserializeEmptyFlightsXml() throws IOException, JAXBException {
        testData = loadTestData("xml/international_flights_none.xml");
        ScheduledFlights noFlights = testObj.deserialize(testData);
        assertThat(noFlights.numberOfFlights(), equalTo(0));
    }

    @Test
    public void testDeserializeAllFlightsXml() throws IOException, JAXBException {
        testData = loadTestData("xml/international_flights.xml");
        ScheduledFlights londonFlights = testObj.deserialize(testData);
        assertThat(londonFlights.numberOfFlights(), equalTo(5));
    }

    @Test
    public void testDesializeNewYorkFlight() throws IOException, JAXBException {
        testData = loadTestData("xml/international_flights_new_york.xml");
        ScheduledFlights newYorkFlights = testObj.deserialize(testData);
        assertThat(newYorkFlights.numberOfFlights(), equalTo(1));
    }

}
