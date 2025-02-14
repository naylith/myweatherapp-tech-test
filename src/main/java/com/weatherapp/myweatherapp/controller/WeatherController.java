package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/forecast/{city}")
    public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {
        CityInfo ci = weatherService.forecastByCity(city);
        return ResponseEntity.ok(ci);
    }

    @GetMapping("/compare-daylight")
    public ResponseEntity<String> compareDaylightHours(@RequestParam String city1, @RequestParam String city2) {
        try {
            String result = weatherService.compareDaylightHours(city1, city2);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/rain-check")
    public ResponseEntity<String> checkRain(@RequestParam String city1, @RequestParam String city2) {
        try {
            String result = weatherService.checkRain(city1, city2);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
