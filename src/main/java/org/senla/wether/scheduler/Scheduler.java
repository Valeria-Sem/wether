package org.senla.wether.scheduler;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.log4j.Logger;
import org.senla.wether.entity.CurrentWeatherEntity;
import org.senla.wether.dto.WeatherDTO;
import org.senla.wether.service.CurrentWeatherService;
import org.senla.wether.service.ServiceException;
import org.senla.wether.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.time.LocalDate;

@Configuration
@EnableScheduling
public class Scheduler {
    private static final Logger LOGGER = Logger.getLogger(Scheduler.class);
    private static final String URL = "https://weatherapi-com.p.rapidapi.com/current.json?q=Minsk";
    private static final String KEY_HEADER_NAME = "X-RapidAPI-Key";
    private static final String HOST_HEADER_NAME = "X-RapidAPI-Host";
    private static final String KEY_HEADER_VALUE = "b2a725632emshef695c3d2d13c71p127919jsnb3d32d9bbf79";
    private static final String HOST_HEADER_VALUE = "weatherapi-com.p.rapidapi.com";



    private final CurrentWeatherService currentWeatherService;

    @Autowired
    public Scheduler(CurrentWeatherService currentWeatherService) {
        this.currentWeatherService = currentWeatherService;
    }

    @Scheduled(cron = "0 */1 * ? * *")
    public void saveWeather() {
        String responseJsonWeatherInfo = downloadingWeatherAccordingSchedule();
        WeatherDTO weatherDTOFromJson = JsonUtils.parseJsonObject(responseJsonWeatherInfo, WeatherDTO.class);

        LOGGER.info(weatherDTOFromJson.toString());
        saveCurrentWeather(weatherDTOFromJson);
    }

    private String downloadingWeatherAccordingSchedule(){
        String returnJSON;
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .addHeader(KEY_HEADER_NAME, KEY_HEADER_VALUE)
                    .addHeader(HOST_HEADER_NAME, HOST_HEADER_VALUE)
                    .build();


            Response response = client.newCall(request).execute();
            returnJSON = response.body().string();
            LOGGER.info(String.format("Response: %s", returnJSON));

            return returnJSON;
        } catch (IOException e) {
            LOGGER.error("An error occurred while downloading the weather");
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    private void saveCurrentWeather(WeatherDTO weatherDTO){
        CurrentWeatherEntity currentWeather = CurrentWeatherEntity.builder()
                .date(LocalDate.now())
                .temperature(weatherDTO.getCurrent().getTempC())
                .windSpeed(weatherDTO.getCurrent().getWindMph())
                .atmosphericPressure(weatherDTO.getCurrent().getPressureMb())
                .airHumidity(weatherDTO.getCurrent().getHumidity())
                .weatherConditions(weatherDTO.getCurrent().getCondition().getText())
                .location(weatherDTO.getLocation().getName())
                .build();


        try {
            LOGGER.info(String.format("Current weather: %s", currentWeather.toString()));
            currentWeatherService.saveCurrentWeather(currentWeather);
            LOGGER.info("Current weather correctly saved");
        } catch (ServiceException e) {
            LOGGER.error("An error occurred while saving the weather");
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}
