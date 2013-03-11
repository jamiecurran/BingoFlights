package com.bingoflights.dao;

import com.bingoflights.model.Flights;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.FileReader;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class FlightsDeserializer {

    public Flights deserialize(String flightsXML) throws JAXBException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("com/bingoflights/model/bindings.xml");

        Map<String,Object> properties = new HashMap<String,Object>();
        properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, inputStream);

        JAXBContext jc = JAXBContext.newInstance(new Class[] {Flights.class}, properties);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        StringReader reader = new StringReader(flightsXML);
        return (Flights) unmarshaller.unmarshal(reader);
    }
}
