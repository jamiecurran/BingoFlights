package com.bingoflights.service;

import com.bingoflights.dao.ScheduledFlightDAO;
import com.bingoflights.dao.utils.FlightsXMLDeserializer;
import exception.ScheduledFlightServiceException;
import org.joda.time.DateMidnight;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import static com.bingoflights.testing.DataLoader.loadTestData;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OutboundFlightServiceITest {

    @Test
    public void testGetFlightDepartingFromCity() throws IOException, ScheduledFlightServiceException, JSONException {
        Calendar flightDate = new DateMidnight(2013, 3, 17).toGregorianCalendar();
        flightDate.setTimeZone(TimeZone.getTimeZone("Etc/GMT-1"));
        String city = "New York";
        String flightData = loadTestData("xml/international_flights.xml");

        ScheduledFlightService scheduledFlightServiceMock = mock(ScheduledFlightService.class);
        when(scheduledFlightServiceMock.getScheduledFlightsForDate(flightDate)).thenReturn(flightData);

        ScheduledFlightDAO scheduledFlightDAO = new ScheduledFlightDAO();
        scheduledFlightDAO.setDeserializer(new FlightsXMLDeserializer());
        scheduledFlightDAO.setScheduledFlightService(scheduledFlightServiceMock);

        OutboundFlightServiceImpl testObj = new OutboundFlightServiceImpl();
        testObj.setScheduledFlightDao(scheduledFlightDAO);

        String flightsJSON = testObj.getFlightsDepartingFromCity(city, flightDate);

        String newYorkFlight = loadTestData("json/new_york_flights.json");
        JSONAssert.assertEquals(newYorkFlight, flightsJSON, true);
    }

}
