package example.mehakmeet.darkskyweather.services;

import example.mehakmeet.darkskyweather.model.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by MEHAKMEET on 24-12-2017.
 */

public interface WeatherServies {

    @GET("{lat},{lng}")
    Call<Weather> getWeather(@Path("lat") double lat,@Path("lng") double lng);

}
