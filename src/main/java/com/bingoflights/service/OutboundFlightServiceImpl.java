package com.bingoflights.service;

import com.bingoflights.dao.ScheduledFlightDAO;
import com.bingoflights.model.ScheduledFlights;
import com.bingoflights.service.utils.FlightsJsonSerializer;
import org.joda.time.DateTimeZone;

import java.util.Calendar;

public class OutboundFlightServiceImpl implements OutboundFlightService {

    private ScheduledFlightDAO scheduledFlightDao;

    @Override
    public String getFlightsDepartingFromCity(String city, Calendar onDate) {
        ScheduledFlights flights = scheduledFlightDao.getFlights(city, onDate);
        FlightsJsonSerializer flightsJsonSerializer = new FlightsJsonSerializer(DateTimeZone.forTimeZone(onDate.getTimeZone()));
        return flightsJsonSerializer.serialize(flights);
    }

    public void setScheduledFlightDao(ScheduledFlightDAO scheduledFlightDao) {
        this.scheduledFlightDao = scheduledFlightDao;
    }

}
