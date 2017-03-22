package com.example.csanchez.myapplication;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;

public class LineupActivity extends AppCompatActivity{
    private String type = "Documentaires";

    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineup);

        type = getIntent().getExtras().getString("type");

        list = (ListView) findViewById(R.id.listView);

        LineupFetcher fetcher = new LineupFetcher();

        fetcher.execute();
    }

    public class LineupFetcher extends AsyncTask<Object, Object, Lineup[]>{

        @Override
        protected Lineup[] doInBackground(Object... params) {
            Lineup[] lineups = new Lineup[0];

            try{
                lineups = WebAPI.getLineups(type);
            }catch(IOException e){
                e.printStackTrace();
            }catch(JSONException e){
                e.printStackTrace();
            }

            return lineups;
        }

        @Override
        protected void onPostExecute(final Lineup[] lineups){
            list.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return lineups.length;
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if(convertView == null){
                        convertView = getLayoutInflater().inflate(R.layout.rangee, parent, false);
                    }
                    TextView title = (TextView) convertView.findViewById(R.id.rangee_title);
                    TextView description = (TextView) convertView.findViewById(R.id.rangee_description);
                    ImageView img = (ImageView) convertView.findViewById(R.id.rangee_image);

                    title.setText(lineups[position].title);
                    description.setText(lineups[position].shortDescription);

                    Picasso.with(getApplicationContext())
                            .load(lineups[position].image)
                            .into(img);
                    return convertView;
                }
            });

        }
    }
}
