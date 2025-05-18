package com.newsportal.backend.service;

import com.newsportal.backend.entity.Weather;
import com.newsportal.backend.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public List<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }

    public List<Weather> getWeatherByCity(String city) {
        // Use a case-insensitive search
        return weatherRepository.findByCityIgnoreCase(city);
    }

    // Add a method to save weather data
    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    public void saveAllWeather(List<Weather> weatherList) {
        weatherRepository.saveAll(weatherList);
    }
}
