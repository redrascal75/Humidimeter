package de.school.humidimeter.logic;

import java.time.LocalDateTime;

public class MeasuredData {

    private int id;
    private int humidity;
    private int temperature;
    private LocalDateTime timeStamp;

    public void setId(int id) {
        this.id = id;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setTimeStamp(String timeStamp) {
        if (timeStamp == null) {
            return;
        }
        int year = Integer.parseInt(timeStamp.substring(0, 4));
        int month = Integer.parseInt(timeStamp.substring(5, 7));
        int day = Integer.parseInt(timeStamp.substring(8, 10));
        int hour = Integer.parseInt(timeStamp.substring(11, 13));
        int min = Integer.parseInt(timeStamp.substring(14, 16));

        this.timeStamp = LocalDateTime.of(year, month, day, hour, min);
    }

    public int getId() {
        return id;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
