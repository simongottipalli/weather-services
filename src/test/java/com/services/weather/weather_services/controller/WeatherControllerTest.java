package com.services.weather.weather_services.controller;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherControllerTest {

    @Test
    public void testGetEventTime() {
        WeatherController weatherController = new WeatherController(null, null);
        LocalDateTime eventTime = weatherController.getEventTime();
        LocalDateTime now = LocalDateTime.now();
        assertTrue(eventTime.isAfter(now) && eventTime.isBefore(now.plusDays(7)), "Event time should be within the next 7 days");
    }
}