package com.bingoflights.dao;

import com.bingoflights.model.ScheduledFlights;
import org.eclipse.persistence.jaxb.JAXBContextProperties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.*;

public class FlightsDeserializer {

    public ScheduledFlights deserialize(String flightsXML) throws JAXBException {
//        List<String> bindings = Arrays.asList("com/bingoflights/model/bindings.xml");
//
//        Map<String,Object> properties = new HashMap<String,Object>();
//        properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, bindings);


//        Map<String, Object> properties = new HashMap<String, Object>(1);
//        properties.put(JAXBContextFactory.ECLIPSELINK_OXM_XML_KEY, "blog/bindingfile/binding.xml");
//        JAXBContext jc = JAXBContext.newInstance("blog.bindingfile", Customer.class.getClassLoader

        Map<String, Object> properties = new HashMap<String, Object>(1);
        properties.put("eclipselink-oxm-xml", "com/bingoflights/model/bindings.xml");
        JAXBContext jc = JAXBContext.newInstance("com.bingoflights.model", ScheduledFlights.class.getClassLoader() , properties);

        //JAXBContext jc = JAXBContext.newInstance();
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        StringReader reader = new StringReader(flightsXML);
        return (ScheduledFlights) unmarshaller.unmarshal(reader);
    }
}
