-- Example SQL script
CREATE DATABASE WeatherDB;
GO

USE WeatherDB;
GO

CREATE Schema Weather;
GO

CREATE TABLE [Weather].Weather (
    EventID INT PRIMARY KEY,
    EventTime DATETIME,
    Lat DECIMAL(9, 2),
    Lon DECIMAL(9, 2),
    AirTemp DECIMAL(5, 2),
    WindSpeed DECIMAL(5, 2),
    LastUpdated DATETIME
);
GO
