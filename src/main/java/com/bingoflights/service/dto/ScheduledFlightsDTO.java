package com.bingoflights.service.dto;

import com.bingoflights.model.Flight;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

public class ScheduledFlightsDTO {

    private Boolean errors;
    private List<Flight> flights;

    public void setErrors(Boolean errors) {
        this.errors = errors;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Boolean getErrors(){
        return errors;
    }

    public List<Flight> getFlights() {
        return flights;
    }
}
