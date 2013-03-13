package com.bingoflights.dao.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

public class FlightsXmlParser {

    private final String data;

    public FlightsXmlParser(String data) {
        this.data = data;
    }

    public String findByCity(String city) throws XPathExpressionException, ParserConfigurationException {
        String query = "//flight[departure/city/text() = '" + city + "']";
        XPath xpath = XPathFactory.newInstance().newXPath();
        InputSource is = new InputSource(new StringReader(data));
        NodeList nodes = (NodeList) xpath.evaluate(query, is, XPathConstants.NODESET);
        return serialiseToString(nodes);
    }

    private String serialiseToString(NodeList nodes) throws ParserConfigurationException {
        Document flights = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element flightsRoot = flights.createElement("flights");
        flights.appendChild(flightsRoot);
        for(int i = 0; i < nodes.getLength(); i++){
            Node copyNode = flights.importNode(nodes.item(i), true);
            flightsRoot.appendChild(copyNode);
        }

        DOMImplementationLS domImplementationLS = (DOMImplementationLS) flights.getImplementation();
        LSSerializer lsSerializer = domImplementationLS.createLSSerializer();
        return lsSerializer.writeToString(flights);
    }
}
