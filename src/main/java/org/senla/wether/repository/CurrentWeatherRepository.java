package org.senla.wether.repository;

import org.senla.wether.entity.CurrentWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CurrentWeatherRepository extends JpaRepository<CurrentWeatherEntity, Integer> {
    CurrentWeatherEntity findByDate(LocalDate date);

    List<CurrentWeatherEntity> findCurrentWeatherEntitiesByDateBetween(LocalDate start, LocalDate end);
}
