package com.example.skysync;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText city;
    TextView temp_status, temperature, rain, wind, humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String city = "Khulna";
        String apiKey = "cd4edd043586c2cc364eb85d4f185e9f";
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apiKey;

        new GetWeatherInfo().execute(url);
    }

   private class GetWeatherInfo extends AsyncTask<String, Void, String > {


       @Override
       protected String doInBackground(String... strings) {
           return null;
       }
   }
}