package com.bingoflights.model;

import org.eclipse.persistence.oxm.annotations.XmlMarshalNullRepresentation;
import org.eclipse.persistence.oxm.annotations.XmlNullPolicy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
public class Flights {

    //@XmlElement(name = "flight")
    //@XmlNullPolicy(nullRepresentationForXml = XmlMarshalNullRepresentation.EMPTY_NODE, emptyNodeRepresentsNull = true)
    private List<Flight> flights;

    public void add(Flight flight) {
        if(flights == null){
            flights = new ArrayList<Flight>();
        }
        flights.add(flight);
    }

    public int numberOfFlights() {
        if(flights == null){
            flights = new ArrayList<Flight>();
        }
        return flights.size();
    }

    public Flight getFlight(int index) {
        return flights.get(index);
    }

    public List<Flight> getFlights() {
        return flights;
    }
}
