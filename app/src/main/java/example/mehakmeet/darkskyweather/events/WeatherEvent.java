package example.mehakmeet.darkskyweather.events;

import example.mehakmeet.darkskyweather.model.Weather;

/**
 * Created by MEHAKMEET on 24-12-2017.
 */

public class WeatherEvent {
    private final Weather weather;


    public WeatherEvent(Weather weather) {

        this.weather=weather;

    }


    public Weather getWeather() {
        return weather;
    }
}
