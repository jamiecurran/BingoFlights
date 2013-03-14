package com.bingoflights.service.utils;

import com.bingoflights.model.Carrier;
import com.bingoflights.model.ScheduledFlights;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class FlightsJsonSerializer {

    private final DateTimeZone timeZone;

    public FlightsJsonSerializer(DateTimeZone timeZone){
        this.timeZone = timeZone;
    }

    public String serialize(ScheduledFlights scheduledFlightsDTO) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(DateTime.class, new DateAdapter(timeZone));
        builder.registerTypeAdapter(Carrier.class, new CarrierAdapter());
        Gson gson = builder.create();
        return gson.toJson(scheduledFlightsDTO);
    }
}
