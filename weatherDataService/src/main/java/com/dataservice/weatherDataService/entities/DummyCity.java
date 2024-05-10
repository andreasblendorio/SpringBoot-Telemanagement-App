package com.dataservice.weatherDataService.entities;

public class DummyCity {
    private String cityName;

    private Long cityLongitude;

    private Long cityLatitude;

    // This will be the string representation of my City Class
    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", cityLongitude=" + cityLongitude +
                ", cityLatitude=" + cityLatitude +
                '}';
    }

    /*
     * if we stop at the toString part,
     * the browser will return null
     * because we have not created any Accessors (Getters&Setters)
     * they play a crucial role in serialization/deserialization
     */

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getCityLongitude() {
        return cityLongitude;
    }

    public void setCityLongitude(Long cityLongitude) {
        this.cityLongitude = cityLongitude;
    }

    public Long getCityLatitude() {
        return cityLatitude;
    }

    public void setCityLatitude(Long cityLatitude) {
        this.cityLatitude = cityLatitude;
    }
}
