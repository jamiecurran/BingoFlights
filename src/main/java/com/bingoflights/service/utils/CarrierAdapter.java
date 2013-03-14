package com.bingoflights.service.utils;

import com.bingoflights.model.Carrier;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CarrierAdapter implements JsonSerializer<Carrier> {

    @Override
    public JsonElement serialize(Carrier src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }
}
