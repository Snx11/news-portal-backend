package com.newsportal.backend.controller;

import com.newsportal.backend.entity.Weather;
import com.newsportal.backend.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<List<Weather>> getWeatherByCity(@RequestParam(required = false) String city) {
        try {
            List<Weather> weatherData;

            if (city == null || city.isEmpty()) {
                // If no city is provided, return all weather data
                weatherData = weatherService.getAllWeather();
            } else {
                // Get weather for the specified city, ignoring case
                weatherData = weatherService.getWeatherByCity(city);

                // If no data found for the requested city, return default data
                if (weatherData.isEmpty()) {
                    // Create some default weather data for the requested city
                    weatherData = createDefaultWeatherData(city);
                }
            }

            return ResponseEntity.ok(weatherData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private List<Weather> createDefaultWeatherData(String city) {
        List<Weather> defaultData = new ArrayList<>();

        // Create 5 days of default weather data
        String[] conditions = {"Sunny", "Partly Cloudy", "Cloudy", "Rainy", "Sunny"};
        int baseTemp = 22; // Base temperature

        // Adjust temperature based on city
        int tempAdjustment = 0;
        if (city.equalsIgnoreCase("Ankara")) {
            tempAdjustment = -2; // Cooler
        } else if (city.equalsIgnoreCase("Izmir")) {
            tempAdjustment = 2;  // Warmer
        } else if (city.equalsIgnoreCase("Antalya")) {
            tempAdjustment = 4;  // Much warmer
        }

        // Generate dates for the next 5 days
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM", new Locale("tr", "TR"));

        for (int i = 0; i < 5; i++) {
            Weather weather = new Weather();
            weather.setCity(city);
            weather.setDate(currentDate.plusDays(i).format(formatter));
            weather.setTemperature((double) (baseTemp + tempAdjustment + (int)(Math.random() * 5) - 2)); // Add some randomness
            weather.setCondition(conditions[i]);
            defaultData.add(weather);
        }

        return defaultData;
    }
}