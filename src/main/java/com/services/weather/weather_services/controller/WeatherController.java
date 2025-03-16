package com.services.weather.weather_services.controller;

import com.services.weather.weather_services.clients.MetClient;
import com.services.weather.weather_services.clients.models.MetResponse;
import com.services.weather.weather_services.models.APIResponse;
import com.services.weather.weather_services.models.DBModel;
import com.services.weather.weather_services.services.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;


@RestController
@RequestMapping("/api")
public class WeatherController {

    private final MetClient metClient;
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(MetClient metClient, WeatherService weatherService) {
        this.metClient = metClient;
        this.weatherService = weatherService;
    }

    @Operation(summary = "Get weather data for an event")
    @GetMapping("/weather")
    public ResponseEntity<APIResponse> getWeather(@RequestParam String eventId, @RequestParam String lat, @RequestParam String lon) throws Exception {
        // TODO: Doesn't work if lat and long changes for the event.
        APIResponse apiResponse = getPersistedWeatherData(eventId, lat, lon);

        if (apiResponse != null) {
            return ResponseEntity.ok(apiResponse);
        }

        LocalDateTime eventTime = getEventTime();
        apiResponse = getNewWeatherData(eventTime, lat, lon);
        if (apiResponse != null) {
            weatherService.insertWeatherData(Integer.parseInt(eventId), eventTime, Double.parseDouble(lat), Double.parseDouble(lon), Double.parseDouble(apiResponse.getAirTemperature()), Double.parseDouble(apiResponse.getWindSpeed()));
            return ResponseEntity.ok(apiResponse);
        }

        apiResponse = new APIResponse(null, null, "No data found");
        return ResponseEntity.internalServerError().body(apiResponse);
    }

    private APIResponse getPersistedWeatherData(String eventId, String lat, String lon) {
        DBModel dbValue = weatherService.getWeatherData(Integer.parseInt(eventId), lat, lon);

        if (dbValue == null) {
            return null;
        }

        LocalDateTime lastUpdated = dbValue.getLastUpdated().toLocalDateTime();

        if (lastUpdated != null && lastUpdated.isAfter(LocalDateTime.now().minusHours(2))) {
            return new APIResponse(String.valueOf(dbValue.getAirTemp()), String.valueOf(dbValue.getWindSpeed()), null);
        }
        return null;
    }

    private LocalDateTime getEventTime() {
        Random random = new Random();
        int randomInt = random.nextInt(7);
        return LocalDateTime.now().plusDays(randomInt);
    }

    private APIResponse getNewWeatherData(LocalDateTime eventTime, String lat, String lon) throws Exception {
        Map<String, String> params = Map.of("lat", lat, "lon", lon);
        Map<String, String> headers = Map.of("User-Agent", "curl/7.68.0");
        MetResponse resp = metClient.getExternalData(params, headers);

        MetResponse.Properties.TimeSeries.Data.Details details = new MetResponse.Properties.TimeSeries.Data.Details();
        for (int i = 0; i < resp.getProperties().getTimeseries().size(); i++) {
            if (resp.getProperties().getTimeseries().get(i).getTime().isBefore(eventTime)) {
                continue;
            }
            details = resp.getProperties().getTimeseries().get(i).getData().getInstant().getDetails();
            break;
        }

        if (details != null) {
            return new APIResponse(String.valueOf(details.getAirTemperature()), String.valueOf(details.getWindSpeed()), null);
        } else {
            return null;
        }
    }
}
