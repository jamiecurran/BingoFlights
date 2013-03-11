package com.bingoflights.service;

import java.util.Calendar;

public interface OutboundFlightService {
    
    String getFlightsDepartingFromCity(String city, Calendar onDate);
    
}
