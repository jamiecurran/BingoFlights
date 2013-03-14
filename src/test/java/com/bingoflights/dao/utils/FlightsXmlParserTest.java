package com.bingoflights.dao.utils;

import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

import static com.bingoflights.testing.DataLoader.loadTestData;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

public class FlightsXmlParserTest {

    @Test
    public void testGetFlightsXmlByCityLondon() throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        String testData = loadTestData("xml/international_flights.xml");
        FlightsXmlParser testObj = new FlightsXmlParser();
        String londonFlights = testObj.findByCity("London", testData);
        String expectedData = loadTestData("xml/international_flights_london.xml");
        XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(londonFlights, expectedData);
    }

    @Test
    public void testGetFlightsXmlByCityNewYork() throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
        String testData = loadTestData("xml/international_flights.xml");
        FlightsXmlParser testObj = new FlightsXmlParser();
        String londonFlights = testObj.findByCity("New York", testData);
        String expectedData = loadTestData("xml/international_flights_new_york.xml");
        XMLUnit.setIgnoreWhitespace(true);
        assertXMLEqual(londonFlights, expectedData);

    }

}
