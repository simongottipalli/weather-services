package com.services.weather.weather_services.models;

public class APIResponse {
    private final String airTemperature;
    private final String windSpeed;
    private final String errorMessage;

    public APIResponse(String airTemperature, String windSpeed, String errorMessage) {
        this.airTemperature = airTemperature;
        this.windSpeed = windSpeed;
        this.errorMessage = errorMessage;
    }

    public String getAirTemperature() {
        return airTemperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
