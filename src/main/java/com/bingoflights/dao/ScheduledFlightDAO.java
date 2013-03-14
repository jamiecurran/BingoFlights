package com.bingoflights.dao;

import com.bingoflights.dao.utils.FlightsDeserializer;
import com.bingoflights.dao.utils.FlightsXmlParser;
import com.bingoflights.model.ScheduledFlights;
import com.bingoflights.service.ScheduledFlightService;
import exception.ScheduledFlightServiceException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.util.Calendar;

public class ScheduledFlightDAO {

    private ScheduledFlightService scheduledFlightService;
    private FlightsXmlParser parser;
    private FlightsDeserializer deserializer;

    public ScheduledFlights getFlights(String city, Calendar flightDate) throws ScheduledFlightServiceException, XPathExpressionException, ParserConfigurationException {
        String scheduledFlights = scheduledFlightService.getScheduledFlightsForDate(flightDate);
        String filterFlightData = parser.findByCity(city, scheduledFlights);
        ScheduledFlights flights = deserializer.deserialize(filterFlightData);
        return flights;
    }

    public void setScheduledFlightService(ScheduledFlightService scheduledFlightService) {
        this.scheduledFlightService = scheduledFlightService;
    }

    public ScheduledFlightService getScheduledFlightService() {
        return scheduledFlightService;
    }

    public void setParser(FlightsXmlParser parser) {
        this.parser = parser;
    }

    public FlightsXmlParser getParser() {
        return parser;
    }

    public void setDeserializer(FlightsDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    public FlightsDeserializer getDeserializer() {
        return deserializer;
    }
}
