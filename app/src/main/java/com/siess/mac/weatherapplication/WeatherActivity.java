package com.siess.mac.weatherapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;

public class WeatherActivity extends AppCompatActivity {
    SwipeRefreshLayout nSwipeRefreshLayout;
    HelperFunctions helperfunctions;
    Context nContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        nContext = this;
        nSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        nSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new JsonRequestTask().execute();
            }
        });
    }

    class JsonRequestTask extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                helperfunctions = new HelperFunctions(nContext, "http://api.openweathermap.org/data/2.5/weather?id=5251436&units=Imperial&APPID=afddd6f84dc91e061fccf71bdf2c03c5");

                return helperfunctions.seperateJSON(helperfunctions.makeRequest());

            } catch (JSONException e) {
                Log.e("JSON", e.getMessage());
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            if (bool){
                helperfunctions.updateScreen();
            }
        }
    }

}
