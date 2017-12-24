package example.mehakmeet.darkskyweather.services;

import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import example.mehakmeet.darkskyweather.events.ErrorEvent;
import example.mehakmeet.darkskyweather.events.WeatherEvent;
import example.mehakmeet.darkskyweather.model.Currently;
import example.mehakmeet.darkskyweather.model.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MEHAKMEET on 24-12-2017.
 */

public class WeatherServiceProvider {

    private static final String BASE_URL = "https://api.darksky.net/forecast/405581373cf6e76893f848a5417cdfb3/";
    private Retrofit retrofit;


    private Retrofit getRetrofit(){

        if(this.retrofit == null){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return this.retrofit;

    }

    public void getWeather(double lat, double lng){
        WeatherServies weatherServies=getRetrofit().create(WeatherServies.class);
        Call<Weather> weatherData=weatherServies.getWeather(lat,lng);
        weatherData.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                if(response.body()!=null) {
                    Weather weather = response.body();
                    Currently currently = weather.getCurrently();
                    Log.e("Current Temp:", currently.getTemperature().toString());

                    EventBus.getDefault().post(new WeatherEvent(weather));
                }
                else
                    EventBus.getDefault().post(new ErrorEvent("No weather Data"));


            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

                EventBus.getDefault().post(new ErrorEvent("Unable to make weather server"));
                }
        });
    }
}
