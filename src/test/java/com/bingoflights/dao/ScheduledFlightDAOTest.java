package com.bingoflights.dao;

import com.bingoflights.dao.utils.FlightsXMLDeserializer;
import com.bingoflights.model.ScheduledFlights;
import com.bingoflights.service.ScheduledFlightService;
import exception.ScheduledFlightServiceException;
import org.joda.time.DateMidnight;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Calendar;

import static com.bingoflights.testing.DataLoader.loadTestData;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ScheduledFlightDAOTest {


    private ScheduledFlightDAO testObj;
    private Calendar flightDate =  new DateMidnight(2013, 3, 17).toGregorianCalendar();
    private String city = "London";
    private FlightsXMLDeserializer deserializer = new FlightsXMLDeserializer();
    private ScheduledFlightService scheduledFlightServiceMock;

    @Before
    public void setup(){
        testObj = new ScheduledFlightDAO();
        testObj.setDeserializer(deserializer);
        scheduledFlightServiceMock = mock(ScheduledFlightService.class);

    }

    @After
    public void tearDown(){
        testObj = null;
        scheduledFlightServiceMock = null;
    }

    @Test
    public void testLondonScheduledFlightsForDate() throws IOException, ScheduledFlightServiceException, XPathExpressionException, ParserConfigurationException {
        String flightData = loadTestData("xml/international_flights.xml");

        when(scheduledFlightServiceMock.getScheduledFlightsForDate(flightDate)).thenReturn(flightData);
        testObj.setScheduledFlightService(scheduledFlightServiceMock);

        ScheduledFlights scheduledFlights = testObj.getFlights(city, flightDate);

        assertThat(scheduledFlights.numberOfFlights(), equalTo(4));
        verify(scheduledFlightServiceMock).getScheduledFlightsForDate(flightDate);
    }

    @Test
    public void testNoScheduledFlightsForDate() throws IOException, ScheduledFlightServiceException, XPathExpressionException, ParserConfigurationException {
        String flightData = loadTestData("xml/international_flights_none.xml");

        when(scheduledFlightServiceMock.getScheduledFlightsForDate(flightDate)).thenReturn(flightData);
        testObj.setScheduledFlightService(scheduledFlightServiceMock);
        testObj.setDeserializer(deserializer);

        ScheduledFlights scheduledFlights = testObj.getFlights(city, flightDate);

        assertThat(scheduledFlights.numberOfFlights(), equalTo(0));
        verify(scheduledFlightServiceMock).getScheduledFlightsForDate(flightDate);
    }

    @Test
    public void testScheduledFlightServiceThrowsError() throws ScheduledFlightServiceException, XPathExpressionException, ParserConfigurationException {
        when(scheduledFlightServiceMock.getScheduledFlightsForDate(flightDate)).thenThrow(new ScheduledFlightServiceException("An error occurred."));
        testObj.setScheduledFlightService(scheduledFlightServiceMock);

        ScheduledFlights scheduledFlights = testObj.getFlights(city, flightDate);

        assertThat(scheduledFlights.hasErrors(), is(true));
        assertThat(scheduledFlights.numberOfFlights(), equalTo(0));
        verify(scheduledFlightServiceMock).getScheduledFlightsForDate(flightDate);
    }

}
