package com.bingoflights.service;

import exception.ScheduledFlightServiceException;
import java.util.Calendar;

public interface ScheduledFlightService {

    String getScheduledFlightsForDate(Calendar calendar) throws ScheduledFlightServiceException;
}
