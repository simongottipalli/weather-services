package com.services.weather.weather_services.models;

import java.sql.Time;
import java.sql.Timestamp;

public class DBModel {
    private int eventId;
    private Timestamp eventTime;
    private double lat;
    private double lon;
    private double airTemp;
    private double windSpeed;
    private Timestamp lastUpdated;

    public DBModel(int eventId, Timestamp eventTime, double lat, double lon, double airTemp, double windSpeed, Timestamp lastUpdated) {
        this.eventId = eventId;
        this.eventTime = eventTime;
        this.lat = lat;
        this.lon = lon;
        this.airTemp = airTemp;
        this.windSpeed = windSpeed;
        this.lastUpdated = lastUpdated;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(double airTemp) {
        this.airTemp = airTemp;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
