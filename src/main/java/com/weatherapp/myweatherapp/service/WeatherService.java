package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class WeatherService {

    @Autowired
    VisualcrossingRepository weatherRepo;

    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public CityInfo forecastByCity(String city) {
        return weatherRepo.getByCity(city);
    }

    public String compareDaylightHours(String city1, String city2) {
        CityInfo cityInfo1 = weatherRepo.getByCity(city1);
        CityInfo cityInfo2 = weatherRepo.getByCity(city2);
        
        try {
            Date sunrise1 = timeFormat.parse(cityInfo1.getSunrise());
            Date sunset1 = timeFormat.parse(cityInfo1.getSunset());
            long daylight1 = sunset1.getTime() - sunrise1.getTime();

            Date sunrise2 = timeFormat.parse(cityInfo2.getSunrise());
            Date sunset2 = timeFormat.parse(cityInfo2.getSunset());
            long daylight2 = sunset2.getTime() - sunrise2.getTime();
            
            if (daylight1 == daylight2) {
                return "Both cities have the same daylight hours.";
            }
            return (daylight1 > daylight2) ? city1 + " has longer daylight hours." : city2 + " has longer daylight hours.";
        } catch (ParseException e) {
            return "Error parsing sunrise/sunset times: " + e.getMessage();
        }
    }

    public String checkRain(String city1, String city2) {
        CityInfo cityInfo1 = weatherRepo.getByCity(city1);
        CityInfo cityInfo2 = weatherRepo.getByCity(city2);
        
        boolean isRaining1 = cityInfo1.getConditions().toLowerCase().contains("rain");
        boolean isRaining2 = cityInfo2.getConditions().toLowerCase().contains("rain");
        
        if (isRaining1 && isRaining2) {
            return "Both cities are experiencing rain.";
        } else if (isRaining1) {
            return city1 + " is currently experiencing rain.";
        } else if (isRaining2) {
            return city2 + " is currently experiencing rain.";
        } else {
            return "Neither city is experiencing rain.";
        }
    }
}