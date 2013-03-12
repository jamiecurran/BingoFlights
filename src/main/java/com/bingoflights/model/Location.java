package com.bingoflights.model;

import com.bingoflights.service.utils.DateAdapter;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

//@XmlAccessorType(XmlAccessType.FIELD)
public class Location {

    private String country;
    private String city;
    private Airport airport;
    private DateTime time;

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public DateTime getTime() {
        return time;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Airport getAirport() {
        return airport;
    }
}