package org.senla.wether.service;

import org.senla.wether.entity.CurrentWeatherEntity;

import java.time.LocalDate;

public interface CurrentWeatherService {
    CurrentWeatherEntity saveCurrentWeather(CurrentWeatherEntity currentWeather) throws ServiceException;

    CurrentWeatherEntity getCurrentWeather() throws ServiceException;

    Double getAverageTemp(LocalDate start, LocalDate end) throws ServiceException;
}
