package com.bingoflights.model;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.*;

//@XmlAccessorType(XmlAccessType.FIELD)
public class Flight {

    //@XmlAttribute(name = "number")
    private String flightNumber;

    //@XmlPath("carrier/name/text()")
    //@XmlElement(name = "carrier")
    private String carrier;

    //@XmlElement(name = "departure")
    private Location departure;

    //@XmlElement(name = "destination")
    private Location destination;

    public String getCarrier() {
        return carrier;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getDeparture() {
        return departure;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Location getDestination() {
        return destination;
    }
}
