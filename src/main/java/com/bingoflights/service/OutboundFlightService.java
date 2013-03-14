package com.bingoflights.service;

import exception.ScheduledFlightServiceException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.util.Calendar;

public interface OutboundFlightService {
    
    String getFlightsDepartingFromCity(String city, Calendar onDate) throws ScheduledFlightServiceException, XPathExpressionException, ParserConfigurationException;

}
