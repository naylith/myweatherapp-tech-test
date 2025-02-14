package com.weatherapp.myweatherapp.service;

import static org.junit.jupiter.api.Assertions.*;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class WeatherServiceTest {

    @Mock
    private VisualcrossingRepository weatherRepo;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCompareDaylightHours() {
        CityInfo city1 = mock(CityInfo.class);
        CityInfo city2 = mock(CityInfo.class);

        when(city1.getSunrise()).thenReturn("07:00:00");
        when(city1.getSunset()).thenReturn("19:00:00");
        when(city2.getSunrise()).thenReturn("07:00:00");
        when(city2.getSunset()).thenReturn("19:00:00");
        when(weatherRepo.getByCity("CityA")).thenReturn(city1);
        when(weatherRepo.getByCity("CityB")).thenReturn(city2);

        String result = weatherService.compareDaylightHours("CityA", "CityB");
        assertEquals("Both cities have the same daylight hours.", result);
    }

    @Test
    void testCheckRain() {
        CityInfo city1 = mock(CityInfo.class);
        CityInfo city2 = mock(CityInfo.class);

        when(city1.getConditions()).thenReturn("Rainy");
        when(city2.getConditions()).thenReturn("Clear");
        when(weatherRepo.getByCity("CityA")).thenReturn(city1);
        when(weatherRepo.getByCity("CityB")).thenReturn(city2);

        String result = weatherService.checkRain("CityA", "CityB");
        assertEquals("CityA is currently experiencing rain.", result);
    }

    @Test
    void testCheckRain_BothRaining() {
        CityInfo city1 = mock(CityInfo.class);
        CityInfo city2 = mock(CityInfo.class);

        when(city1.getConditions()).thenReturn("Rain");
        when(city2.getConditions()).thenReturn("Rain showers");
        when(weatherRepo.getByCity("CityA")).thenReturn(city1);
        when(weatherRepo.getByCity("CityB")).thenReturn(city2);

        String result = weatherService.checkRain("CityA", "CityB");
        assertEquals("Both cities are experiencing rain.", result);
    }
}
