package com.bingoflights.service.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.lang.reflect.Type;

public class DateAdapter implements JsonSerializer<DateTime> {

    private final DateTimeZone timeZone;

    public DateAdapter(DateTimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
        DateTimeFormatter format = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm Z");
        DateTime localTime = src.withZone(timeZone);
        return new JsonPrimitive(localTime.toString(format));
    }
}
