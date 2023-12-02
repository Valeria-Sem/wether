package org.senla.wether.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "current_weather", schema = "weather")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CurrentWeather {
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "id")
    private LocalDate date;

    @Basic
    @Column(name = "temperature")
    private int temperature;

    @Basic
    @Column(name = "wind_speed")
    private double windSpeed;

    @Basic
    @Column(name = "atmospheric_pressure")
    private double atmosphericPressure;

    @Basic
    @Column(name = "air_humidity")
    private double airHumidity;

    @Basic
    @Column(name = "weather_conditions")
    private double weatherConditions;

    @ManyToOne
    @JoinColumn(name = "id_location")
    private LocationEntity location;

}
