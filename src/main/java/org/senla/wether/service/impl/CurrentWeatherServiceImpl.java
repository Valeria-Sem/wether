package org.senla.wether.service.impl;

import org.senla.wether.entity.CurrentWeatherEntity;
import org.senla.wether.repository.CurrentWeatherRepository;
import org.senla.wether.service.CurrentWeatherService;
import org.senla.wether.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrentWeatherServiceImpl implements CurrentWeatherService {
    private static final Logger LOGGER = Logger.getLogger(CurrentWeatherServiceImpl.class);
    private final CurrentWeatherRepository currentWeatherRepository;

    @Autowired
    public CurrentWeatherServiceImpl(CurrentWeatherRepository currentWeatherRepository) {
        this.currentWeatherRepository = currentWeatherRepository;
    }

    @Override
    public CurrentWeatherEntity saveCurrentWeather(CurrentWeatherEntity currentWeather) throws ServiceException {
        LOGGER.info("Try to save current weather");
        return currentWeatherRepository.save(currentWeather);
    }

    @Override
    public CurrentWeatherEntity getCurrentWeather() throws ServiceException {
        LOGGER.info("Try to get current weather");
        return currentWeatherRepository.findByDate(LocalDate.now());
    }

    @Override
    public Double getAverageTemp(LocalDate start, LocalDate end) throws ServiceException {
        LOGGER.info("Try to get average temperature");
        List<CurrentWeatherEntity> weathers = currentWeatherRepository
                .findCurrentWeatherEntitiesByDateBetween(start, end);

        LOGGER.info("Average temperature correctly get");
        return weathers.stream()
                .collect(Collectors.averagingDouble(CurrentWeatherEntity::getTemperature));
    }
}
