package com.bingoflights.service;

import com.bingoflights.dao.ScheduledFlightDAO;
import com.bingoflights.dao.utils.FlightsXMLDeserializer;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import exception.ScheduledFlightServiceException;
import org.joda.time.DateMidnight;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static spark.Spark.after;
import static spark.Spark.get;


public class FlightsController {

    public static void main(String[] args) {

        get(new Route("/"){

            public Object handle(Request request, Response response){
                URL indexHtml = Resources.getResource("index.html");
                String html = null;
                try {
                    html = Resources.toString(indexHtml, Charsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    html = e.getMessage();
                };
                return html;
            }

        });

        get(new Route("/flights"){

            public Object handle(Request request, Response response) {
                OutboundFlightServiceImpl flightService = new OutboundFlightServiceImpl();
                URL allFlightsURL = Resources.getResource("xml/international_flights.xml");
                String flights = null;
                try {
                    flights = Resources.toString(allFlightsURL, Charsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                Calendar flightDate = new DateMidnight(2013, 3, 17).toGregorianCalendar();
                flightDate.setTimeZone(TimeZone.getTimeZone("Etc/GMT-1"));
                ScheduledFlightService scheduledFlightServiceMock = mock(ScheduledFlightService.class);
                try {
                    when(scheduledFlightServiceMock.getScheduledFlightsForDate(flightDate)).thenReturn(flights);
                } catch (ScheduledFlightServiceException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                ScheduledFlightDAO dao = new ScheduledFlightDAO();
                dao.setDeserializer(new FlightsXMLDeserializer());
                dao.setScheduledFlightService(scheduledFlightServiceMock);

                flightService.setScheduledFlightDao(dao);

                response.type("application/json");
                String json = flightService.getFlightsDepartingFromCity(request.queryParams("city"), flightDate);
                return json;
            }

        });
    }


}
