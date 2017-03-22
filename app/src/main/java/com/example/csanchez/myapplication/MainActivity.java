package com.example.csanchez.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button emissions     = (Button)findViewById(R.id.btn_emissions);
        Button films         = (Button)findViewById(R.id.btn_films);
        Button documentaires = (Button)findViewById(R.id.btn_documentaires);
        Button episodes      = (Button)findViewById(R.id.btn_episodes);

        emissions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LineupActivity.class);
                intent.putExtra("type", "Emissions");
                startActivity(intent);
            }
        });

        films.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LineupActivity.class);
                intent.putExtra("type", "Films");
                startActivity(intent);
            }
        });

        documentaires.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LineupActivity.class);
                intent.putExtra("type", "Documentaires");
                startActivity(intent);
            }
        });

        episodes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LineupActivity.class);
                intent.putExtra("type", "Episodes");
                startActivity(intent);
            }
        });
    }



}
