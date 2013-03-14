package com.bingoflights.model;

public class Flight {

    private String flightNumber;
    private Carrier carrier;
    private Location departure;
    private Location destination;

    public Carrier getCarrier() {
        return carrier;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setCarrier(Carrier carrier) {
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
