package com.rtrk.v1;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rtrk.R;
import com.rtrk.weather.Weather;
import com.rtrk.weather.WeatherFeedParser;

public class SummerWeatherActivity extends Activity {
	
	static String feedUrl = "http://api.wunderground.com/auto/wui/geo/ForecastXML/index.xml?query=";
	
	WeatherFeedParser parser;
    TextView weatherText;
    List<Weather> weatherList;
    
     @Override
     /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v1_main);
        
        weatherText = (TextView)findViewById(R.id.weatherText);
        WeatherTask task = new WeatherTask();
        task.execute();
    }
     
    class WeatherTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
	        parser = new WeatherFeedParser(feedUrl+"Novi%20Sad");
	        weatherList = parser.parse();
			return null;
		}
    	
		@Override
		protected void onPostExecute(Void result) {
	        if(weatherList.size()>0) {
	        	weatherText.setText(weatherList.get(0).getConditions());
	            Log.d("V1", "Found weather info!");
	        } else {
	            Log.d("V1", "NO WEATHER INFO!");
	        }
		}
    }
}