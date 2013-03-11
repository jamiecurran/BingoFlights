package com.bingoflights.dao;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FlightsXmlParserTest {

    @Test
    public void testGetFlightsXmlByCityLondon() throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {

        //String query = "//flight[departure/city/text() = 'London']";
        URL allFlightsURL = Resources.getResource("xml/international_flights.xml");
        String testData = Resources.toString(allFlightsURL, Charsets.UTF_8);
        FlightsXmlParser testObj = new FlightsXmlParser(testData);
        String londonFlights = testObj.findByCity("London");
        URL londonFlightsURL = Resources.getResource("xml/international_flights_london.xml");
        String expectedData = Resources.toString(londonFlightsURL, Charsets.UTF_8);
        //System.out.println(expectedData);
        System.out.println(londonFlights);

        //assertXMLEqual(londonFlights, expectedData);
    }



    @Test
    public void testGetFlightsXmlByTime(){

    }

    @Test
    public void testGetFlightsXmlByCityAndTime(){

    }

}
