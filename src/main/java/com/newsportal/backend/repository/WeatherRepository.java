package com.newsportal.backend.repository;

import com.newsportal.backend.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    @Query(value = "SELECT * FROM weather WHERE LOWER(city) = LOWER(:city)", nativeQuery = true)
    List<Weather> findByCityIgnoreCase(@Param("city") String city);
}
