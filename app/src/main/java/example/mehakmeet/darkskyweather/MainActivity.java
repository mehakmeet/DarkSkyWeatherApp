package example.mehakmeet.darkskyweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.mehakmeet.darkskyweather.events.ErrorEvent;
import example.mehakmeet.darkskyweather.events.WeatherEvent;
import example.mehakmeet.darkskyweather.model.Currently;
import example.mehakmeet.darkskyweather.model.Weather;
import example.mehakmeet.darkskyweather.services.WeatherServiceProvider;
import example.mehakmeet.darkskyweather.services.WeatherServies;
import example.mehakmeet.darkskyweather.util.WeathericonUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.temp_txt)
    TextView temp;

    @BindView(R.id.icon_img)
    ImageView ic;

    @BindView(R.id.summary_txt)
    TextView summ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

           setContentView(R.layout.activity_main);
        requestCurrentWeather(12.9165167,79.13249859999996);

        ButterKnife.bind(this);
        temp.setVisibility(View.INVISIBLE);
        ic.setVisibility(View.INVISIBLE);
        summ.setVisibility(View.INVISIBLE);

    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(WeatherEvent weatherEvent) {
        Currently currently= weatherEvent.getWeather().getCurrently();
        temp.setText(String.valueOf(Math.round(currently.getTemperature())));
        temp.setVisibility(View.VISIBLE);
        summ.setText(currently.getSummary());
        ic.setImageResource(WeathericonUtil.ICONS.get(currently.getIcon()));
        ic.setVisibility(View.VISIBLE);
        summ.setVisibility(View.VISIBLE);

    }
    @Subscribe(threadMode =ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent errorEvent){
        Toast.makeText(this,errorEvent.getErrorMessage(),Toast.LENGTH_LONG).show();

    }

    private void requestCurrentWeather(double lat, double lng) {

        WeatherServiceProvider weatherServiceProvider=new WeatherServiceProvider();
        weatherServiceProvider.getWeather(lat,lng);
    }
}
