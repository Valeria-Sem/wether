package org.senla.wether;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.senla.wether.entity.CurrentWeatherEntity;
import org.senla.wether.service.CurrentWeatherService;
import org.senla.wether.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CurrentWeatherServiceTest {
    @MockBean
    CurrentWeatherService mocCurrentWeatherService;

    @Autowired
    public CurrentWeatherServiceTest(CurrentWeatherService mocCurrentWeatherService) {
        this.mocCurrentWeatherService = mocCurrentWeatherService;
    }

    @Test
    void saveCurrentWeather() throws ServiceException {
        CurrentWeatherEntity mockCurrentWeather = Mockito.mock(CurrentWeatherEntity.class);

        mockCurrentWeather.setId(1);
        mockCurrentWeather.setDate(LocalDate.now());
        mockCurrentWeather.setTemperature(-7);
        mockCurrentWeather.setWindSpeed(190);
        mockCurrentWeather.setAtmosphericPressure(23456);
        mockCurrentWeather.setAirHumidity(234);
        mockCurrentWeather.setWeatherConditions("Cloudy");
        mockCurrentWeather.setLocation("Minsk");


        when(mocCurrentWeatherService.saveCurrentWeather(mockCurrentWeather)).thenReturn(mockCurrentWeather);

        assertEquals(mockCurrentWeather, mocCurrentWeatherService.saveCurrentWeather(mockCurrentWeather));
    }

    @Test
    void getCurrentDate() throws ServiceException {
        CurrentWeatherEntity mockCurrentWeather = Mockito.mock(CurrentWeatherEntity.class);

        mockCurrentWeather.setId(1);
        mockCurrentWeather.setDate(LocalDate.now());
        mockCurrentWeather.setTemperature(-7);
        mockCurrentWeather.setWindSpeed(190);
        mockCurrentWeather.setAtmosphericPressure(23456);
        mockCurrentWeather.setAirHumidity(234);
        mockCurrentWeather.setWeatherConditions("Cloudy");
        mockCurrentWeather.setLocation("Minsk");


        when(mocCurrentWeatherService.getCurrentWeather()).thenReturn(mockCurrentWeather);

        assertEquals(mockCurrentWeather, mocCurrentWeatherService.getCurrentWeather());
    }

    @Test
    void getAverageTemp() throws ServiceException {
        when(mocCurrentWeatherService.getAverageTemp(LocalDate.now(), LocalDate.now().plusDays(2)))
                .thenReturn(-7.0);

        assertEquals(-7.0, mocCurrentWeatherService
                .getAverageTemp(LocalDate.now(), LocalDate.now().plusDays(2)));
    }

}
