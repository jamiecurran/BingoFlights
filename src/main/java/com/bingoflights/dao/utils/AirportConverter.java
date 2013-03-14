package com.bingoflights.dao.utils;

import com.bingoflights.model.Airport;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class AirportConverter implements Converter {
    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Airport airport = new Airport();
        airport.setCode(reader.getAttribute("code"));
        airport.setName(reader.getValue());
        return airport;
    }

    @Override
    public boolean canConvert(Class type) {
        return Airport.class.isAssignableFrom(type);
    }
}
