package com.bingoflights.model;

import java.util.ArrayList;
import java.util.List;

public class ScheduledFlights {

    private List<Flight> flights = new ArrayList<Flight>();

    public void add(Flight flight) {
        flights.add(flight);
    }

    public int numberOfFlights() {
        return flights.size();
    }

    public List<Flight> getFlights() {
        return flights;
    }
}
