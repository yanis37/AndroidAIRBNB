package com.example.projetairbnb;

import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MyAsyncTask extends AsyncTask<Object, Void, String> {

    private URL url;
    private HttpsURLConnection urlConnection;
    private String message;
    List<Airbnb> airbnbList = new ArrayList<Airbnb>();
    AirbnbAdapter adapter;


    @Override
    protected String doInBackground(Object... params) {

        String str = (String) params[0];
        airbnbList = (List<Airbnb>) params[1];
        adapter = (AirbnbAdapter) params[2];


        try {
            url = new URL(str);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                // chargement du flux
                InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
                BufferedReader input = new BufferedReader(isr);
                StringBuilder stringBuilder = new StringBuilder();
                String temp;
                while ((temp = input.readLine()) != null) {
                    stringBuilder.append(temp);
                }
                String jsonStr = stringBuilder.toString();
                parsJSON(jsonStr);

                urlConnection.disconnect();
                // parsage du flux
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void parsJSON(String str){
        String defaut = "?";

        try{
            JSONObject jsonObject = new JSONObject(str);
            JSONArray records = jsonObject.getJSONArray("records");
            Log.d("size", String.valueOf(records.length()));

            for (int i=0; i<records.length(); i++) {
                JSONObject fields = records.getJSONObject(i).getJSONObject("fields");

                String name = fields.optString("name", defaut);
                String city = fields.optString("city", defaut);
                String price = fields.optString("price", defaut);
                String property = fields.optString("property_type", defaut);
                String picture = fields.optString("thumbnail_url", defaut);



                Log.d("TAG", name);
                Log.d("TAG", city);
                Log.d("TAG", property);
                Log.d("TAG", price);

                Airbnb airbnb = new Airbnb(name, city, property, price, picture);

                airbnbList.add(airbnb);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("aaaaa", airbnbList.get(0).getPrice());
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        adapter.notifyDataSetChanged();
    }
}
