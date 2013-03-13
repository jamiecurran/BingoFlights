package com.bingoflights.dao.utils;

import com.bingoflights.model.Airport;
import com.bingoflights.model.Flight;
import com.bingoflights.model.Location;
import com.bingoflights.model.ScheduledFlights;
import com.thoughtworks.xstream.XStream;
import org.eclipse.persistence.jaxb.JAXBContextProperties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamConstants;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.*;

public class FlightsDeserializer {

    public ScheduledFlights deserialize(String flightsXML){
        XStream xStream = new XStream();
        xStream.alias("flights", ScheduledFlights.class);
        xStream.addImplicitCollection(ScheduledFlights.class, "flights");
        xStream.useAttributeFor(Flight.class, "flightNumber");
        xStream.aliasAttribute("number", "flightNumber");
        xStream.alias("flight", Flight.class);
        xStream.alias("depature", Location.class);
        xStream.alias("destination", Location.class);
        xStream.alias("airport", Airport.class);
        xStream.aliasField("name", Flight.class, "carrier/name");

        xStream.omitField(Location.class, "service_company");
        return (ScheduledFlights) xStream.fromXML(flightsXML);
    }
}
