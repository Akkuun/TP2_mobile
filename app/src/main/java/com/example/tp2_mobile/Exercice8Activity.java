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
        countries.add(new Country("France"));
        countries.add(new Country("Spain"));
        countries.add(new Country("Germany"));
        countries.add(new Country("Italy"));
        countries.add(new Country("USA"));

        // Créer un adaptateur et l'associer à la RecyclerView
        CountryAdapter adapter = new CountryAdapter(countries, country -> {
            // Lorsque le pays est cliqué, démarrer la deuxième activité avec les détails
            Intent intent = new Intent(Exercice8Activity.this, Exercice8Activity_detail.class);
            intent.putExtra("country_name", country.getName()); // Passer le nom du pays
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
