package com.example.csanchez.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csanchez.myapplication.TouTv.Lineup;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    ListView list;

    MyAdapter adapter;
    Lineup films;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.listView);

        RunAPI run = new RunAPI();
        run.execute();

    }

    public class RunAPI extends AsyncTask<String, Object, Lineup>{

        @Override
        protected Lineup doInBackground(String... params) {
            System.out.println("DOING BACKGROUND STUFF");
            WebAPI web = new WebAPI();
            try{
                films =  web.run();

            }
            catch(IOException e){
                e.printStackTrace();
            }
            return films;
        }

        @Override
        protected void onPostExecute(Lineup lineup) {
            super.onPostExecute(lineup);
            adapter = new MyAdapter();
            list.setAdapter(adapter);

        }
    }

    public class MyAdapter extends BaseAdapter{
        LayoutInflater inflater;

        public MyAdapter(){
            inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return films.LineupItems.size();
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

            View v = convertView;

            if(v == null){
                v = inflater.inflate(R.layout.rangee, parent, false);
            }

            TextView tv = (TextView) v.findViewById(R.id.rangee_text);
            ImageView iv = (ImageView) v.findViewById(R.id.rangee_image);

            String title = films.LineupItems.get(position).Title;
            String imgURL = films.LineupItems.get(position).ImageUrl;

            tv.setText(title);
            Picasso.with(getApplicationContext()).load(imgURL).into(iv);
            return v;
        }
    }

}
