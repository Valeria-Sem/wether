package org.senla.wether.controller;

import org.apache.log4j.Logger;
import org.senla.wether.service.CurrentWeatherService;
import org.senla.wether.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/currentWeather")
public class CurrentWeatherController {
    private static class ExceptionMessage{
        private static final String SERVER_EXCEPTION_TEXT = "Server error occurred during the execution of the request";

    }
    private static final Logger LOGGER = Logger.getLogger(CurrentWeatherController.class);

    private final CurrentWeatherService currentWeatherService;

    @Autowired
    public CurrentWeatherController(CurrentWeatherService currentWeatherService) {
        this.currentWeatherService = currentWeatherService;
    }

    @GetMapping()
    public ResponseEntity<?> getCurrentWeather() {
        try {
            return new ResponseEntity<>(currentWeatherService.getCurrentWeather(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOGGER.error("CurrentWeatherController (getCurrentWeather) -> some problems with extracting current weather");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionMessage.SERVER_EXCEPTION_TEXT);
        }
    }

    @GetMapping("/averageTemp")
    public ResponseEntity<?> getAverageTemp(@RequestParam(name = "startDate")LocalDate start,
                                               @RequestParam(name = "endDate")LocalDate end) {
        try {
            return new ResponseEntity<>(currentWeatherService.getAverageTemp(start, end), HttpStatus.OK);
        } catch (ServiceException e) {
            LOGGER.error("CurrentWeatherController (getAverageTemp) -> some problems with extracting average temperature");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionMessage.SERVER_EXCEPTION_TEXT);
        }
    }
}
