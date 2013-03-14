package com.bingoflights.dao.utils;

import com.bingoflights.model.Airport;
import com.bingoflights.model.Flight;
import com.bingoflights.model.Location;
import com.bingoflights.model.ScheduledFlights;
import com.thoughtworks.xstream.XStream;

public class FlightsXMLDeserializer {

    private static int EMPTY_DESERIALISED_FLIGHT = 0;

    public ScheduledFlights deserialize(String flightsXML){
        XStream xStream = new XStream();
        xStream.alias("flights", ScheduledFlights.class);
        xStream.addImplicitCollection(ScheduledFlights.class, "flights");
        xStream.useAttributeFor(Flight.class, "flightNumber");
        xStream.aliasAttribute("number", "flightNumber");
        xStream.alias("flight", Flight.class);
        xStream.registerConverter(new DateTimeConverter());
        xStream.alias("depature", Location.class);
        xStream.alias("destination", Location.class);
        xStream.registerConverter(new AirportConverter());
        xStream.alias("airport", Airport.class);
        xStream.omitField(Location.class, "service_company");
        ScheduledFlights scheduledFlights = (ScheduledFlights) xStream.fromXML(flightsXML);
        filterEmptyFlights(scheduledFlights);
        return scheduledFlights;
    }

    private void filterEmptyFlights(ScheduledFlights scheduledFlights) {

        Flight flight = scheduledFlights.getFlights().get(EMPTY_DESERIALISED_FLIGHT);
        if(flight.getFlightNumber() == null && flight.getCarrier() == null && flight.getDeparture() == null && flight.getDestination() == null){
            scheduledFlights.getFlights().remove(EMPTY_DESERIALISED_FLIGHT);
        }
    }
}
