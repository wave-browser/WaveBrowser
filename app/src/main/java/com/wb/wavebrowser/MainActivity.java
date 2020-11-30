package com.wb.wavebrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.location.Location;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Response;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.kwabenaberko.openweathermaplib.constants.Units;
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper;
import com.kwabenaberko.openweathermaplib.implementation.callbacks.CurrentWeatherCallback;
import com.kwabenaberko.openweathermaplib.implementation.callbacks.ThreeHourForecastCallback;
import com.kwabenaberko.openweathermaplib.models.common.Weather;
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather;
import com.kwabenaberko.openweathermaplib.models.threehourforecast.ThreeHourForecast;
import com.kwabenaberko.openweathermaplib.network.OpenWeatherMapClient;
import com.kwabenaberko.openweathermaplib.network.OpenWeatherMapService;
import com.squareup.picasso.*;
import org.json.JSONObject;
import org.w3c.dom.Text;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

import de.mrapp.android.tabswitcher.LayoutPolicy;
import de.mrapp.android.tabswitcher.TabSwitcher;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;
import static com.wb.wavebrowser.R.color.white;
public class MainActivity extends AppCompatActivity {
    private View coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        TextView cityText = (TextView)findViewById(R.id.cityText);
        TextView forecast = (TextView)findViewById(R.id.testoNE);
        OpenWeatherMapHelper helper = new OpenWeatherMapHelper(getString(R.string.OPEN_WEATHER_MAP_API_KEY));
        FloatingActionButton btnButtonTabs = (FloatingActionButton) findViewById(R.id.btnTabsButton);
        //TextView foreCatst = (TextView)findViewById(R.id.foreCast);
        Context  context = getBaseContext();
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
        Intent searchWebTest = new Intent(Intent.ACTION_VIEW, null, MainActivity.this, webWave.class);
        Intent fastSearch = new Intent(Intent.ACTION_VIEW, null, MainActivity.this, com.wb.wavebrowser.fastSearch.class);

        ShortcutInfo shortcut = new ShortcutInfo.Builder(context, "id1")
                .setShortLabel("Website")
                .setLongLabel("Search website  ")
                .setIcon(Icon.createWithResource(context, R.drawable.ic_add_circle_black_18dp))
                .setIntent(searchWebTest)
                .build();
        ShortcutInfo shortcut2 = new ShortcutInfo.Builder(context, "id2")
                .setShortLabel("Github")
                .setLongLabel("GitHub ")
                .setIcon(Icon.createWithResource(context, R.drawable.ic_memory_black_18dp))
                .setIntent(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.mysite.example.com/")))
                .build();
        ShortcutInfo shortcut3 = new ShortcutInfo.Builder(context, "id3")
                .setShortLabel("Fast search")
                .setLongLabel("Fast search ")
                .setIcon(Icon.createWithResource(context, R.drawable.ic_baseline_search_24))
                .setIntent(fastSearch)
                .build();
        shortcutManager.setDynamicShortcuts(Arrays.asList(shortcut, shortcut2,shortcut3));
        //Buttons
        btnButtonTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Creating", Toast.LENGTH_SHORT);
                Intent tabView = new Intent(MainActivity.this,  webWave.class);
                MainActivity.this.startActivity(tabView);
            }
        });
        //OpenWheaterApi
        helper.setUnits(Units.METRIC);
        helper.getThreeHourForecastByCityName("London",
                new ThreeHourForecastCallback() {
                    @Override
                    public void onSuccess(ThreeHourForecast threeHourForecast) {
                     }
                    @Override
                    public void onFailure(Throwable throwable) {
                     }
                });
        helper.getCurrentWeatherByCityName("London", new CurrentWeatherCallback(){

            @Override
            public void onSuccess(CurrentWeather currentWeather)
            {
                                        cityText.setText(
                        "City: " + currentWeather.getName() + "\n" +
                                "Country:"  +  currentWeather.getSys().getCountry() + "\n" + "_____________________"
                );
                forecast.setText(
                        "Clouds:"  + currentWeather.getWeather().get(0).getDescription() +
                                "\n" + "Wind Degree:"+currentWeather.getWind().getDeg()+"°"
                                + "\n" + "Wind Speed:"+currentWeather.getWind().getSpeed() + "\n"
                                +"Temp:" + currentWeather.getMain().getTemp() + "°C"
                );

            }
            @Override
            public void onFailure(Throwable throwable) {
                cityText.setText( throwable.getMessage());
            }
        });


    }

}
