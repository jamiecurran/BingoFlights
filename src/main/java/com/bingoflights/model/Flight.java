package com.bingoflights.model;

public class Flight {

    private String flightNumber;
    private String carrier;
    private Location departure;
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
