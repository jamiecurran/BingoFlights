package com.bingoflights.service;

import com.bingoflights.model.FlightsDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class FlightsJsonSerializer {


    public String serialize(FlightsDTO flightsDTO) throws JAXBException {
        Map<String, Object> properties = new HashMap<String, Object>(2);
        properties.put("eclipselink-oxm-xml", "com/bingoflights/model/bindings.json");
        properties.put("eclipselink.media-type", "application/json");
        properties.put("eclipselink.json.include-root",false);
        JAXBContext context = JAXBContext.newInstance("com.bingoflights.model", FlightsDTO.class.getClassLoader() , properties);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(flightsDTO, writer);
        return writer.toString();
    }
}
