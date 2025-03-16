package com.services.weather.weather_services.services;

import com.services.weather.weather_services.models.DBModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class WeatherService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WeatherService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertWeatherData(int eventId, LocalDateTime eventTime, double lat, double lon, double airTemp, double windSpeed) {
        Timestamp eventTimeDB = java.sql.Timestamp.valueOf(eventTime);
        Timestamp now = java.sql.Timestamp.valueOf(LocalDateTime.now());
        String sql = "INSERT INTO Weather.Weather (EventID, EventTime, Lat, Lon, AirTemp, WindSpeed, LastUpdated) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, eventId, eventTimeDB, lat, lon, airTemp, windSpeed, now);
    }

   public DBModel getWeatherData(int eventId, String lat, String lon) {
       String sql = "SELECT * FROM Weather.Weather WHERE EventID = ?";
       try {
           return jdbcTemplate.queryForObject(sql, new Object[]{eventId}, (rs, rowNum) -> new DBModel(
                   rs.getInt("EventID"),
                   rs.getTimestamp("EventTime"),
                   rs.getDouble("Lat"),
                   rs.getDouble("Lon"),
                   rs.getDouble("AirTemp"),
                   rs.getDouble("WindSpeed"),
                   rs.getTimestamp("LastUpdated")
           ));
       } catch (EmptyResultDataAccessException e) {
           return null;
       }
   }
}