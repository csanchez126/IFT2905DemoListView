package com.example.csanchez.myapplication;

import android.widget.Toast;

import com.example.csanchez.myapplication.TouTv.Lineup;
import com.example.csanchez.myapplication.TouTv.Root;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WebAPI {
    public String url;

    public WebAPI(){
        url = "http://nasv3d.iro.umontreal.ca/toutv/section-populaires.json";
    }

    public Lineup run() throws IOException{
        //  recuperer du contenu HTML à partir d'un URL
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        String json = response.body().string();

        //Parse JSON from string JSON
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Root> jsonAdapter = moshi.adapter(Root.class);
        Root root = jsonAdapter.fromJson(json);

        //On va chercher les films
        Lineup films = new Lineup();
        for(int i=0; i<root.Lineups.size(); i++){
            if(root.Lineups.get(i).Title.equals("Films")){
                films = root.Lineups.get(i);
                return films;
            }
        }
        return null;

    }
}
