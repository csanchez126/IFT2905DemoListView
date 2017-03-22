package com.example.csanchez.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebAPI {

    public static String url = "http://nasv3d.iro.umontreal.ca/toutv/section-populaires.json";
    public static OkHttpClient httpClient;
    public static JSONObject json=null;

    private static JSONObject getJSON() throws IOException, JSONException {
        if(json!=null){
            return json;
        }

        Request request = new Request.Builder().url(url).build();

        if(httpClient == null){
            httpClient = new OkHttpClient();
        }
        Response response = httpClient.newCall(request).execute();

        json = new JSONObject(response.body().string());

        return json;
    }

    public static Lineup[] getLineups(String type) throws IOException, JSONException{
        JSONObject json = getJSON();
        JSONArray categories = json.getJSONArray("Lineups");
        JSONObject category = null;

        for(int i=0; i<categories.length(); i++){
            category = categories.getJSONObject(i);
            if(categories.getJSONObject(i).getString("Title").equals(type)){
                break;
            }
        }

        JSONArray jsonLineups = category.getJSONArray("LineupItems");

        Lineup[] lineups = new Lineup[jsonLineups.length()];

        for(int i=0; i<lineups.length; i++){

            JSONObject lineup = jsonLineups.getJSONObject(i);
            lineups[i] = new Lineup(
                    lineup.getString("Title"),
                    lineup.getString("Description"),
                    lineup.getString("ImageUrl"),
                    lineup.getJSONObject("Share").getString("AbsoluteUrl"),
                    lineup.getJSONObject("Details").getString("Description")
            );
        }
        return lineups;
    }

}
