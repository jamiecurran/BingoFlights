package com.bingoflights.dao.utils;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

/**
 * Created with IntelliJ IDEA.
 * User: jamie
 * Date: 22/03/13
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public interface FlightsParser {
    String findByCity(String city) throws XPathExpressionException, ParserConfigurationException;
}
