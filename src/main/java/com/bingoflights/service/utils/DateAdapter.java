package com.bingoflights.service.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter implements JsonSerializer<DateTime> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm Z");

    @Override
    public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
        DateTimeFormatter format = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm Z");
        return new JsonPrimitive(src.toString(format));
    }
}
