package com.weatherapp.myweatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CityInfo {

  @JsonProperty("address")
  String address;

  @JsonProperty("description")
  String description;

  @JsonProperty("currentConditions")
  CurrentConditions currentConditions;

  @JsonProperty("days")
  List<Days> days;

  static class CurrentConditions {
    @JsonProperty("temp")
    String currentTemperature;

    @JsonProperty("sunrise")
    String sunrise;

    @JsonProperty("sunset")
    String sunset;

    @JsonProperty("feelslike")
    String feelslike;

    @JsonProperty("humidity")
    String humidity;

    @JsonProperty("conditions")
    String conditions;
  }

  static class Days {
    @JsonProperty("datetime")
    String date;

    @JsonProperty("temp")
    String currentTemperature;

    @JsonProperty("tempmax")
    String maxTemperature;

    @JsonProperty("tempmin")
    String minTemperature;

    @JsonProperty("conditions")
    String conditions;
  }

  public String getSunrise() {
    return currentConditions.sunrise;
  }

  public String getSunset() {
    return currentConditions.sunset;
  }

  public String getConditions() {
    return currentConditions.conditions;
  }
}
