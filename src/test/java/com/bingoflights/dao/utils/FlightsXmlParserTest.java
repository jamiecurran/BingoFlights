package com.bingoflights.dao.utils;

import org.custommonkey.xmlunit.XMLUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

import static com.bingoflights.testing.DataLoader.loadTestData;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

public class FlightsXmlParserTest {

    private FlightsXmlParser testObj;
    private String testData;
    private String expectedData;

    @Before
    public void setup(){
        XMLUnit.setIgnoreWhitespace(true);
    }

    @After
    public void tearDown(){
        testObj = null;
        testData = null;
        expectedData = null;
    }

    @Test
    public void testGetFlightsXmlByCityLondon() throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        testData = loadTestData("xml/international_flights.xml");
        testObj = new FlightsXmlParser(testData);

        String londonFlights = testObj.findByCity("London");
        expectedData = loadTestData("xml/international_flights_london.xml");
        assertXMLEqual(londonFlights, expectedData);
    }

    @Test
    public void testGetFlightsXmlByCityNewYork() throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        testData = loadTestData("xml/international_flights.xml");
        testObj = new FlightsXmlParser(testData);
        String newYorkFlights = testObj.findByCity("New York");
        expectedData = loadTestData("xml/international_flights_new_york.xml");
        assertXMLEqual(newYorkFlights, expectedData);

    }

}
