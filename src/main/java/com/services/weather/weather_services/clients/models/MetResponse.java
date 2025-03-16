package com.services.weather.weather_services.clients.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class MetResponse {
    private String type;
    private Geometry geometry;
    private Properties properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static class Geometry {
        private String type;
        private List<Double> coordinates;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }
    }

    public static class Properties {
        private Meta meta;
        private List<TimeSeries> timeseries;

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }

        public List<TimeSeries> getTimeseries() {
            return timeseries;
        }

        public void setTimeseries(List<TimeSeries> timeseries) {
            this.timeseries = timeseries;
        }

        public static class Meta {
            @JsonProperty("updated_at")
            private String updatedAt;
            private Map<String, String> units; //TODO: Change to a more appropriate type

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public Map<String, String> getUnits() {
                return units;
            }

            public void setUnits(Map<String, String> units) {
                this.units = units;
            }
        }

        public static class TimeSeries {
            private String time;
            private Data data;

            public LocalDateTime getTime() {
                return LocalDateTime.ofInstant(Instant.parse(time), ZoneId.systemDefault());
            }

            public void setTime(String time) {
                this.time = time;
            }

            public Data getData() {
                return data;
            }

            public void setData(Data data) {
                this.data = data;
            }

            public static class Data {
                private Instant instant;
                private Summary next_12_hours;
                private Summary next_1_hours;
                private Summary next_6_hours;

                public Instant getInstant() {
                    return instant;
                }

                public void setInstant(Instant instant) {
                    this.instant = instant;
                }

                public Summary getNext_12_hours() {
                    return next_12_hours;
                }

                public void setNext_12_hours(Summary next_12_hours) {
                    this.next_12_hours = next_12_hours;
                }

                public Summary getNext_1_hours() {
                    return next_1_hours;
                }

                public void setNext_1_hours(Summary next_1_hours) {
                    this.next_1_hours = next_1_hours;
                }

                public Summary getNext_6_hours() {
                    return next_6_hours;
                }

                public void setNext_6_hours(Summary next_6_hours) {
                    this.next_6_hours = next_6_hours;
                }

                public static class Instant {
                    private Details details;

                    public Details getDetails() {
                        return details;
                    }

                    public void setDetails(Details details) {
                        this.details = details;
                    }
                }

                public static class Summary {
                    private String symbol_code;
                    private Details details;

                    public String getSymbol_code() {
                        return symbol_code;
                    }

                    public void setSymbol_code(String symbol_code) {
                        this.symbol_code = symbol_code;
                    }

                    public Details getDetails() {
                        return details;
                    }

                    public void setDetails(Details details) {
                        this.details = details;
                    }
                }

                public static class Details {
                    @JsonProperty("air_pressure_at_sea_level")
                    private Double airPressureAtSeaLevel;
                    @JsonProperty("air_temperature")
                    private Double airTemperature;
                    @JsonProperty("cloud_area_fraction")
                    private Double cloudAreaFraction;
                    @JsonProperty("relative_humidity")
                    private Double relativeHumidity;
                    @JsonProperty("wind_from_direction")
                    private Double windFromDirection;
                    @JsonProperty("wind_speed")
                    private Double windSpeed;

                    public Double getAirPressureAtSeaLevel() {
                        return airPressureAtSeaLevel;
                    }
                    public void setAirPressureAtSeaLevel(Double airPressureAtSeaLevel) {
                        this.airPressureAtSeaLevel = airPressureAtSeaLevel;
                    }

                    public Double getAirTemperature() {
                        return airTemperature;
                    }

                    public void setAirTemperature(Double airTemperature) {
                        this.airTemperature = airTemperature;
                    }

                    public Double getCloudAreaFraction() {
                        return cloudAreaFraction;
                    }

                    public void setCloudAreaFraction(Double cloudAreaFraction) {
                        this.cloudAreaFraction = cloudAreaFraction;
                    }

                    public Double getRelativeHumidity() {
                        return relativeHumidity;
                    }

                    public void setRelativeHumidity(Double relativeHumidity) {
                        this.relativeHumidity = relativeHumidity;
                    }

                    public Double getWindFromDirection() {
                        return windFromDirection;
                    }

                    public void setWindFromDirection(Double windFromDirection) {
                        this.windFromDirection = windFromDirection;
                    }

                    public Double getWindSpeed() {
                        return windSpeed;
                    }

                    public void setWindSpeed(Double windSpeed) {
                        this.windSpeed = windSpeed;
                    }
                }
            }
        }
    }
}