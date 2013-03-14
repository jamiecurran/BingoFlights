package com.bingoflights.model;

import java.util.ArrayList;
import java.util.List;

public class ScheduledFlights {

    private Boolean errors = Boolean.FALSE;

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

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Flight getFlight(int index){
        return flights.get(index);
    }

    public Boolean hasErrors() {
        return errors;
    }

    public void setErrors(Boolean errors) {
        this.errors = errors;
    }
}
