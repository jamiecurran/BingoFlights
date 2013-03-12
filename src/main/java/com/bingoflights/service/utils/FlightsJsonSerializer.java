package com.bingoflights.service.utils;

import com.bingoflights.model.ScheduledFlights;
import com.bingoflights.service.dto.ScheduledFlightsDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FlightsJsonSerializer {


    public String serialize(ScheduledFlightsDTO scheduledFlightsDTO) throws JAXBException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(Date.class, new DateAdapter());

        Gson gson = builder.create();
        return gson.toJson(scheduledFlightsDTO);
    }
}
