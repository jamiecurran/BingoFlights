package com.bingoflights.dao.utils;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.joda.time.DateTime;

public class DateTimeConverter implements Converter {

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return new DateTime(reader.getValue());
    }

    @Override
    public boolean canConvert(Class type) {
        return DateTime.class.isAssignableFrom(type);
    }
}
