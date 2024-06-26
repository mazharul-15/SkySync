package com.example.skysync;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

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
       protected String doInBackground(String... urls) {

           HttpsURLConnection connection = null;
           BufferedReader reader = null;

           try {

               URL url = new URL(urls[0]);
               connection = (HttpsURLConnection) url.openConnection();
               connection.connect();

               reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
               StringBuilder buffer = new StringBuilder();
               String line;

               while((line = reader.readLine()) != null) {
                   buffer.append(line).append("\n");
               }

               return buffer.toString();

           } catch (IOException e) {
                e.printStackTrace();
           } finally {
               if(connection != null) {
                   connection.disconnect();
               }

               try {
                   if(reader != null) {
                       reader.close();
                   }
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

           return null;
       }

       @Override
       protected void onPostExecute(String result) {
           super.onPostExecute(result);
           if(result != null) {
               try {
                   JSONObject jsonObject = new JSONObject(result);
                   JSONObject main = jsonObject.getJSONObject("main");

                   double temp = main.getDouble("temp") - 273.00;
                   String tempValue = Double.toString(temp);
                   //String tempValue = "" + temp;

                   temperature = findViewById(R.id.temperature);
                   temperature.setText(tempValue);

               }catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       }
   }
}