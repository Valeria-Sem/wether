package org.senla.wether.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherDTO {
    @SerializedName(value = "location")
    private Location location;

    @SerializedName(value = "current")
    private Current current;

    @Data
    @Builder
    public static class Location {
        @SerializedName(value = "name")
        private String name;
    }

    @Data
    @Builder
    public static class Current {
        @SerializedName(value = "temp_c")
        private double tempC;

        @SerializedName(value = "condition")
        private Condition condition;

        @SerializedName(value = "wind_mph")
        private double windMph;

        @SerializedName(value = "pressure_mb")
        private double pressureMb;

        @SerializedName(value = "humidity")
        private double humidity;

        @Data
        @Builder
        public static class Condition {
            @SerializedName(value = "text")
            private String text;
        }
    }
}
