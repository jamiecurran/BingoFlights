package com.bingoflights.dao;

import com.bingoflights.dao.utils.FlightsXMLDeserializer;
import com.bingoflights.dao.utils.FlightsXmlParser;
import com.bingoflights.model.ScheduledFlights;
import com.bingoflights.service.ScheduledFlightService;

import java.util.Calendar;

public class ScheduledFlightDAO {

    private ScheduledFlightService scheduledFlightService;
    private FlightsXMLDeserializer deserializer;

    public ScheduledFlights getFlights(String city, Calendar flightDate){
        try{
            String scheduledFlights = scheduledFlightService.getScheduledFlightsForDate(flightDate);
            FlightsXmlParser parser = new FlightsXmlParser(scheduledFlights);
            if(parser.hasFlightsFromCity(city)){
                String filterFlightData = parser.findByCity(city);
                return deserializer.deserialize(filterFlightData);
            } else {
                return new ScheduledFlights();
            }
        }
        catch (Exception e){
            ScheduledFlights errorFlight = new ScheduledFlights();
            errorFlight.setErrors(true);
            return errorFlight;
        }
    }

    public void setScheduledFlightService(ScheduledFlightService scheduledFlightService) {
        this.scheduledFlightService = scheduledFlightService;
    }

    public void setDeserializer(FlightsXMLDeserializer deserializer) {
        this.deserializer = deserializer;
    }
}
