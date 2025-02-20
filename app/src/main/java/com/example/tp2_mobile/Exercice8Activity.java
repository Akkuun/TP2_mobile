package com.example.tp2_mobile;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Exercice8Activity extends AppCompatActivity {

   RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.exercice8);

        //clickable list of contry
        recyclerView = findViewById(R.id.recycleView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //fill the recycler view with the list of country clickable
        List<String> countries = List.of("France", "Germany", "Italy", "Spain", "United Kingdom", "United States");
        //set the adapter
        recyclerView.setAdapter(new CountryAdapter(
                countries,
                country -> {
                    //when a country is clicked
                    //display the name of the country in a toast
                    Toast.makeText(this, country, Toast.LENGTH_SHORT).show();
                }
        ));



    }

}

