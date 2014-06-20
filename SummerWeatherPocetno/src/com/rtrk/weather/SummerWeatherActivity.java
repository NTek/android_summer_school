package com.rtrk.weather;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rtrk.R;
import com.rtrk.weather.beans.Weather;
import com.rtrk.weather.beans.WeatherFeedParser;

import java.util.List;

public class SummerWeatherActivity extends Activity {
    private static final String TAG = "SummerWeatherActivity";
    private static final String FEED_URL = "http://api.wunderground.com/auto/wui/geo/ForecastXML/index.xml?query=";
    private WeatherFeedParser mParser = null;
    private TextView mWeatherText = null;
    private List<Weather> mWeatherList = null;

    @Override
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);
        mWeatherText = (TextView) findViewById(R.id.weatherText);
        WeatherTask task = new WeatherTask();
        task.execute();
    }

    class WeatherTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            mParser = new WeatherFeedParser(FEED_URL + "Novi%20Sad");
            mWeatherList = mParser.parse();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (mWeatherList.size() > 0) {
                mWeatherText.setText(mWeatherList.get(0).getConditions());
                Log.d(TAG, "Found weather info!");
            } else {
                Log.d(TAG, "NO WEATHER INFO!");
            }
        }
    }
}