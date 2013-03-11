package com.bingoflights.dao.moxy;

import com.bingoflights.dao.FlightsDeserializer;
import com.bingoflights.model.Flights;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class FlightsDeserializerTest {

    private String testData;
    private FlightsDeserializer testObj;

    @Before
    public void setup(){
        testObj = new FlightsDeserializer();
    }

    @Test
    public void testDeserializeEmptyFlightsXml() throws IOException, JAXBException {
        testData = loadTestData("xml/international_flights_none.xml");
        Flights noFlights = testObj.deserialize(testData);
        assertThat(noFlights.numberOfFlights(), is(0));
    }

    @Test
    public void testDeserializeAllFlightsXml() throws IOException, JAXBException {
        testData = loadTestData("xml/international_flights.xml");
        Flights londonFlights = testObj.deserialize(testData);
        assertThat(londonFlights.numberOfFlights(), is(5));
    }

    @Test
    public void testDesializeNewYorkFlight() throws IOException, JAXBException {
        testData = loadTestData("xml/international_flights_new_york.xml");
        Flights newYorkFlights = testObj.deserialize(testData);
        assertThat(newYorkFlights.numberOfFlights(), is(1));
    }

    private String loadTestData(String file) throws IOException {
        URL flightsXmlUrl = Resources.getResource(file);
        return Resources.toString(flightsXmlUrl, Charsets.UTF_8);
    }

}
