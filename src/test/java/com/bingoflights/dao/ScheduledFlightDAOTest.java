package com.bingoflights.dao;

import com.bingoflights.dao.utils.FlightsDeserializer;
import com.bingoflights.dao.utils.FlightsXmlParser;
import com.bingoflights.model.Flight;
import com.bingoflights.model.ScheduledFlights;
import com.bingoflights.service.ScheduledFlightService;
import exception.ScheduledFlightServiceException;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import static com.bingoflights.testing.DataLoader.loadTestData;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ScheduledFlightDAOTest {

    @Test
    public void testLondonScheduledFlightsForDate() throws IOException, ScheduledFlightServiceException, XPathExpressionException, ParserConfigurationException {
        ScheduledFlightDAO testObj = new ScheduledFlightDAO();
        ScheduledFlightService scheduledFlightServiceMock = mock(ScheduledFlightService.class);
        String flightData = loadTestData("xml/international_flights.xml");
        Calendar testCalendar = Calendar.getInstance();
        testCalendar.set(2013,3,17);
        String city = "London";

        when(scheduledFlightServiceMock.getScheduledFlightsForDate(testCalendar)).thenReturn(flightData);
        testObj.setScheduledFlightService(scheduledFlightServiceMock);

        FlightsDeserializer deserializer = new FlightsDeserializer();
        FlightsXmlParser parser = new FlightsXmlParser();
        testObj.setParser(parser);
        testObj.setDeserializer(deserializer);
        ScheduledFlights scheduledFlights = testObj.getFlights(city, testCalendar);


        List<Flight> cityFlights = scheduledFlights.getFlights();
        assertThat(cityFlights.size(), is(4));
        for(Flight flight: cityFlights){
            assertThat(flight.getDeparture().getCity(), is("London"));
        }
        verify(scheduledFlightServiceMock).getScheduledFlightsForDate(testCalendar);
    }

    @Test
    public void testNoScheduledFlightsForDate() throws IOException, ScheduledFlightServiceException, XPathExpressionException, ParserConfigurationException {
        ScheduledFlightDAO testObj = new ScheduledFlightDAO();
        ScheduledFlightService scheduledFlightServiceMock = mock(ScheduledFlightService.class);
        String flightData = loadTestData("xml/international_flights_none.xml");
        Calendar testCalendar = Calendar.getInstance();
        testCalendar.set(2013,3,18);
        String city = "London";

        when(scheduledFlightServiceMock.getScheduledFlightsForDate(testCalendar)).thenReturn(flightData);
        testObj.setScheduledFlightService(scheduledFlightServiceMock);

        FlightsDeserializer deserializer = new FlightsDeserializer();
        FlightsXmlParser parser = new FlightsXmlParser();
        testObj.setParser(parser);
        testObj.setDeserializer(deserializer);
        ScheduledFlights scheduledFlights = testObj.getFlights(city, testCalendar);


        List<Flight> cityFlights = scheduledFlights.getFlights();
        assertThat(cityFlights.size(), is(0));
        verify(scheduledFlightServiceMock).getScheduledFlightsForDate(testCalendar);
    }

}
