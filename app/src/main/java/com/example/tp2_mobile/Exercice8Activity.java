package com.example.tp2_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2_mobile.Country;
import com.example.tp2_mobile.CountryAdapter;

import java.util.ArrayList;
import java.util.List;

public class Exercice8Activity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercice8);

        recyclerView = findViewById(R.id.recyclerView);

        // Créer une liste de pays
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("France", "flag_france", "Paris", 67000000));
        countries.add(new Country("Spain", "flag_spain", "Madrid", 47000000));
        countries.add(new Country("Germany", "flag_germany", "Berlin", 83000000));
        countries.add(new Country("Italy", "flag_italy", "Rome", 60000000));
        countries.add(new Country("USA", "flag_usa", "Washington", 328000000));

        // adapter to display the countries in a recycler view
        CountryAdapter adapter = new CountryAdapter(countries, country -> {
            // when a country is clicked, open the detail activity
            Intent intent = new Intent(Exercice8Activity.this, Exercice8Activity_detail.class);
            intent.putExtra("country_name", country.getName()); // pass country name to the next activity
            intent.putExtra("country_flag", country.getFlag()); // pass country flag to the next activity
            intent.putExtra("country_capital", country.getCapital()); // pass country capital to the next activity
            intent.putExtra("country_population", country.getPopulation()); // pass country population to the next activity
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
