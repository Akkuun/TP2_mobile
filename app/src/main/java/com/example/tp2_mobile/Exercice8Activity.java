package com.example.tp2_mobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Exercice8Activity extends AppCompatActivity implements CountriesListFragment.OnCountrySelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercice8);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState == null) {
                // Ajouter le fragment de la liste des pays
                CountriesListFragment countriesListFragment = new CountriesListFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, countriesListFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onCountrySelected(Country country) {
        // Afficher les détails du pays dans le fragment des détails
        CountriesDetailFragment detailsFragment = CountriesDetailFragment.newInstance(country);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack(null) // Ajouter à la pile arrière pour navigation
                .commit();
    }
}
